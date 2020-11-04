package edu.uncc.itcs.lightbulblms.service;

import edu.uncc.itcs.lightbulblms.controller.model.response.AllUsersResponse;
import edu.uncc.itcs.lightbulblms.service.model.User;
import edu.uncc.itcs.lightbulblms.service.model.response.OktaUsersResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Value("${userservice.hostname}")
    private String hostname;

    @Value("${userservice.adminGroup}")
    private String adminGroup;

    @Value("${userservice.studentGroup}")
    private String studentGroup;

    @Value("${userservice.teacherGroup}")
    private String teacherGroup;

    @Value("${userservice.apikey}")
    private String apiKey;

    private List<User> adminUsers;
    private List<User> studentUsers;
    private List<User> teacherUsers;

    @PostConstruct
    private void init() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("SSWS %s", apiKey));
        HttpEntity requestEntity = new HttpEntity(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(hostname)
                .path("/api/v1/groups/{groupID}/users");


        ResponseEntity<OktaUsersResponse[]> adminUsersResponse = restTemplate.exchange(uriComponentsBuilder
                .buildAndExpand(adminGroup).toUri(), HttpMethod.GET, requestEntity, OktaUsersResponse[].class);
        adminUsers = Arrays.stream(adminUsersResponse.getBody()).map(User::new).collect(Collectors.toList());

        ResponseEntity<OktaUsersResponse[]> studentUsersResponse = restTemplate.exchange(uriComponentsBuilder
                .buildAndExpand(studentGroup).toUri(), HttpMethod.GET, requestEntity, OktaUsersResponse[].class);
        studentUsers = Arrays.stream(studentUsersResponse.getBody()).map(User::new).collect(Collectors.toList());

        ResponseEntity<OktaUsersResponse[]> teacherUsersResponse = restTemplate.exchange(uriComponentsBuilder
                .buildAndExpand(teacherGroup).toUri(), HttpMethod.GET, requestEntity, OktaUsersResponse[].class);
        teacherUsers = Arrays.stream(studentUsersResponse.getBody()).map(User::new).collect(Collectors.toList());
    }

    public AllUsersResponse getAllUsers() {
        return new AllUsersResponse(adminUsers, studentUsers, teacherUsers);
    }
}
