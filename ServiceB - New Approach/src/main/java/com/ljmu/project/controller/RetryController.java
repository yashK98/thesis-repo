package com.ljmu.project.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ljmu.project.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class RetryController {

    @Autowired
    private RetryService retryService;

    @GetMapping("/reprocessFailedEvents")
    public List<JsonNode> reprocessFailedEvents() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return retryService.reprocessFailedEvents();
    }
}
