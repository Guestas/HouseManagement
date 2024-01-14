package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.User;
import com.mc.HouseManagement.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public Long addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<User> loadAllUsers() {
        return userDAO.loadAllUsers();
    }
}
