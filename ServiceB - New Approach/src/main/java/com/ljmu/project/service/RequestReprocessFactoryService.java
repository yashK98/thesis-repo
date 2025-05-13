package com.ljmu.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ljmu.project.gateway.EntityGateway;
import com.ljmu.project.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
@Service
public class RequestReprocessFactoryService {

    @Autowired
    private EntityGateway entityGateway;

    public void requestReprocessor(JsonNode request, String methodName, Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try{
            Method method = EntityGateway.class.getMethod(methodName, clazz);
            method.invoke(entityGateway, clazz.cast(JsonUtil.convertJsonNodeToObject(request, clazz)));
        }catch (Exception ex){
            log.info("Service is still down, will reprocess after sometime :: {}", ex.getMessage());
        }
    }
}
