package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.User;

import java.util.List;

public interface UserDAO {
    public Long addUser(User user);
    public User getUserById(Long id);
    public List<User> loadAllUsers();
}
