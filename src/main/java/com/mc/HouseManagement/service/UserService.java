package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.User;

import java.util.List;

public interface UserService {
    public Long addUser(User user);
    public User getUserById(Long id);
    public List<User> loadAllUsers();
}
