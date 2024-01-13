package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDAOImpl implements UserDAO{

    private final EntityManager entityManager;
    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long addUser(User user) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> loadAllUsers() {
        return null;
    }
}
