package com.example.demowebmvc.service;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.example.demowebmvc.dto.user.UserListResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
    name = "mode",
    havingValue = "default",
    matchIfMissing = true
)
public class UserServiceImplA implements UserService{
    private final RemoteApiCall remoteApiCall;

    @Override
    public List<UserListResponse> users() {
        String url = "https://reqres.in/api/users";
        JsonNode node = remoteApiCall.get(url);
        ArrayNode dataNode = (ArrayNode) node.get("data");
        
        Stream<JsonNode>  streamNode = StreamSupport.stream(Spliterators
                  .spliteratorUnknownSize(dataNode.elements(),
                        Spliterator.ORDERED),false);
                        
        return streamNode
        .map(UserListResponse::convert)
        .collect(Collectors.toList());
    }
    
}
