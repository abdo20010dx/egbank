package com.banking.egbank.modules.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banking.egbank.modules.user.entities.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(UserEntity userEntity) {
        entityManager.persist(userEntity);
    }

    public UserEntity findById(Integer id) {
        return entityManager.find(UserEntity.class, id);
    }

    public List<UserEntity> findAll() {
        TypedQuery<UserEntity> usersQuery = entityManager.createQuery("FROM UserEntity", UserEntity.class);
        return usersQuery.getResultList();
    }

    public List<UserEntity> findByName(String name) {
        TypedQuery<UserEntity> usersQuery = entityManager.createQuery("FROM UserEntity WHERE name=:nameParam",
                UserEntity.class);
        usersQuery.setParameter("nameParam", name);
        return usersQuery.getResultList();
    }

    @Transactional
    public void update(UserEntity user) {
        entityManager.merge(user);
    }

    @Transactional
    public void deleteById(Integer id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        entityManager.remove(user);
    }

    @Transactional
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM UserEntity").executeUpdate();

    }

}
