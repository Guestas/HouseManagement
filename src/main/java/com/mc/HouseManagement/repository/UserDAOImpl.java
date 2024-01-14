package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private final EntityManager entityManager;
    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Long addUser(User user) {
        return entityManager.merge(user).getId();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> loadAllUsers() {
        TypedQuery<User> query = entityManager
                .createQuery("SELECT u FROM User u", User.class);
        List<User> result = query.getResultList();
        return result.isEmpty()?null:result;
    }

    @Override
    @Transactional
    public int deleteAllUsers() {
        Query query = entityManager.createQuery("DELETE FROM User");
        return query.executeUpdate();
    }
}
