package com.banking.egbank.modules.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.banking.egbank.modules.user.dao.UserDao;
import com.banking.egbank.modules.user.entities.UserEntity;
import com.banking.egbank.shared.common.BaseModule.BaseService;

@Primary
@Component
public class UserService extends BaseService implements UserInterface {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void create(UserEntity user) {
        this.userDao.save(user);
    }

    public void updateById(int id, UserEntity user) {
        user.setId(id);
        this.userDao.update(user);
    }

    public UserEntity findById(int id) {
        return this.userDao.findById(id);
    }

    public List<UserEntity> findAll() {
        return this.userDao.findAll();
    }

    public void deleteById(int id) {
        this.userDao.deleteById(id);
    }

    public int deleteAll() {
        return this.userDao.deleteAll();
    }

}
