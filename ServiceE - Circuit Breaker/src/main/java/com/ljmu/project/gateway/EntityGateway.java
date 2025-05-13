package com.ljmu.project.gateway;

import com.ljmu.project.model.Post;
import com.ljmu.project.model.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class EntityGateway {

    private String CREATE_USER_ENDPOINT = "/createUser";
    private String CREATE_POST_ENDPOINT = "/createPost";

    private RestTemplate restTemplate;

    @Autowired
    public EntityGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${ljmu.microserviceA.url}")
    private String baseUrl;

    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallbackResponse")
    public ResponseEntity<?> createUserResponse(User user){
        String url = baseUrl + CREATE_USER_ENDPOINT;
        HttpEntity<?> httpEntity = new HttpEntity<>(user, this.getHttpHeaders());
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return response;
    }

    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallbackResponse")
    public ResponseEntity<?> createPostResponse(Post post){
        String url = baseUrl + CREATE_POST_ENDPOINT;
        HttpEntity<?> httpEntity = new HttpEntity<>(post, this.getHttpHeaders());
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return response;
    }


    public ResponseEntity<?> fallbackResponse(Exception e) {
        log.info("Fallback triggered due to :: {}", e.getStackTrace());
        return new ResponseEntity<>("Transaction Failed!!!..... Circuit is broken", HttpStatusCode.valueOf(500));
    }

    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return headers;
    }
}
