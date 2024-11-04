package com.ljmu.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ljmu.project.entity.RequestAudit;
import com.ljmu.project.model.Post;
import com.ljmu.project.repository.RequestAuditRepo;
import com.ljmu.project.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RetryService {

    @Autowired
    private RequestAuditRepo requestAuditRepo;

    @Autowired
    private RequestReprocessFactoryService requestReprocessFactoryService;

    public List<JsonNode> reprocessFailedEvents() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<RequestAudit> failedRequestsList = requestAuditRepo.getFailedRequests();
        List<JsonNode> reprocessList = new ArrayList<>();
        if(ObjectUtils.isEmpty(failedRequestsList)){
            log.info("There are no failed events to be processed");
        } else{
            log.info("No. of Failed Events :: {}", failedRequestsList.size());
            for(RequestAudit requestAudit : failedRequestsList){
                JsonNode response = JsonUtil.convertStringToJson(requestAudit.getRequestData());
                reprocessList.add(response);
                requestReprocessFactoryService.requestReprocessor(response, requestAudit.getMethodName(), Post.class);
            }
        }
        return reprocessList;
    }

}
