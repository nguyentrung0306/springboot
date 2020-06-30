package com.example.springboot.service;

import com.example.springboot.DAO.UserDAO;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDao;

    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }

    public User addUser(User user) {
        return this.userDao.save(user);
    }

    public void deleteUserById(Integer id) {
        this.userDao.deleteById(id);
    }
}
