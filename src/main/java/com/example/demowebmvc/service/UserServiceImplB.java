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
    havingValue = "serviceB"
)
public class UserServiceImplB implements UserService {
    private final RemoteApiCall remoteApiCall;

    @Override
    public List<UserListResponse> users() {
        String url = "https://fakestoreapi.com/users";
        JsonNode node = remoteApiCall.get(url);
        ArrayNode dataNode = (ArrayNode) node;
        
        Stream<JsonNode>  streamNode = StreamSupport.stream(Spliterators
                  .spliteratorUnknownSize(dataNode.elements(),
                        Spliterator.ORDERED),false);
                        
        return streamNode
        .map(UserListResponse::to)
        .collect(Collectors.toList());
    }
    
}
