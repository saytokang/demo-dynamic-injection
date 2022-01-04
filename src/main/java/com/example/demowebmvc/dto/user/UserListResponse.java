package com.example.demowebmvc.dto.user;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class UserListResponse {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    public static UserListResponse convert(JsonNode node) {
        log.info("--0---\n{}", node.toPrettyString());

        return UserListResponse.builder()
        .id(node.get("id").asInt())
        .email(node.get("email").asText())
        .firstName(node.get("first_name").asText())
        .lastName(node.get("last_name").asText())
        .avatar(node.get("avatar").asText())
        .build();
    }


    public static UserListResponse to(JsonNode node) {
        log.info("--0---\n{}", node.toPrettyString());

        return UserListResponse.builder()
        .id(node.get("id").asInt())
        .email(node.get("email").asText())
        .firstName(node.get("name").get("firstname").asText())
        .lastName(node.get("name").get("lastname").asText())
        .avatar("")
        .build();
    }
}
