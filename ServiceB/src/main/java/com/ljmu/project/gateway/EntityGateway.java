package com.ljmu.project.gateway;

import com.ljmu.project.model.Post;
import com.ljmu.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.ljmu.project.util.ServiceConstants.CREATE_POST_ENDPOINT;
import static com.ljmu.project.util.ServiceConstants.CREATE_USER_ENDPOINT;

@Component
public class EntityGateway {

    private RestTemplate restTemplate;

    @Autowired
    public EntityGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${ljmu.microserviceA.url}")
    private String baseUrl;

    public ResponseEntity<?> createUserResponse(User user){
        String url = baseUrl + CREATE_USER_ENDPOINT;
        HttpEntity<?> httpEntity = new HttpEntity<>(user, this.getHttpHeaders());
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return response;
    }

    public ResponseEntity<?> createPostResponse(Post post){
        String url = baseUrl + CREATE_POST_ENDPOINT;
        HttpEntity<?> httpEntity = new HttpEntity<>(post, this.getHttpHeaders());
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return response;
    }

    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return headers;
    }
}
