package com.example.demowebmvc.service;

import java.util.List;

import com.example.demowebmvc.dto.user.UserListResponse;

public interface UserService {

    List<UserListResponse> users();
    
}
