package com.ljmu.project.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljmu.project.entity.RequestAudit;
import com.ljmu.project.repository.RequestAuditRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.ljmu.project.util.RetryConstants.FAILED;
import static com.ljmu.project.util.RetryConstants.SUCCESS;


@Slf4j
@Aspect
@Component
public class GatewayAspect {

    @Autowired
    private RequestAuditRepo requestAuditRepo;

    private final ObjectMapper objectMapper;

    public GatewayAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Around("execution(public * com.ljmu.project.gateway.EntityGateway.*(..))")
    public Object gatewayAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String requestData = null;
        try {
            if (args.length > 0) {
                requestData = objectMapper.writeValueAsString(args[0]);
            }
        } catch (JsonProcessingException e) {
            log.error("Failed to convert request data to JSON: {}", e.getMessage(), e);
        }
        log.info("Executing method:: {} with arguments:: {}", methodName, args);
        Object response;
        try {
            response = joinPoint.proceed();
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) response;
            log.info("Method :: {} executed successfully with response :: {}", methodName, responseEntity.getBody());
            saveAudit(this.successAudit(methodName, requestData, responseEntity));
        } catch (Exception e) {
            log.error("Method {} failed with error: {}", methodName, e.getMessage(), e);
            saveAudit(this.failureAudit(methodName, requestData, e));
            throw e;
        }
        return response;
    }

    private RequestAudit successAudit(String methodName, String requestData, ResponseEntity<?> response){
        RequestAudit requestAudit = new RequestAudit();
        requestAudit.setMethodName(methodName);
        requestAudit.setRequestData(requestData);
        requestAudit.setResponseStatus(response.getStatusCode().toString());
        if(ObjectUtils.isNotEmpty(response.getBody())){
            requestAudit.setResponseBody(requestAudit.getResponseBody());
        }
        requestAudit.setStatus(SUCCESS);
        requestAudit.setCreationTimestamp(LocalDate.now().toString());
        return requestAudit;
    }

    private RequestAudit failureAudit(String methodName, String requestData, Exception e){
        RequestAudit requestAudit = new RequestAudit();
        requestAudit.setMethodName(methodName);
        requestAudit.setRequestData(requestData);
        requestAudit.setStatus(FAILED);
        requestAudit.setCreationTimestamp(LocalDate.now().toString());
        requestAudit.setException(e.getMessage());
        requestAudit.setExceptionMessage(e.getCause().getMessage());
        return requestAudit;
    }


    private void saveAudit(RequestAudit requestAudit){
        requestAuditRepo.save(requestAudit);
    }

}
