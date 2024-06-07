package com.zhaksylyk;

import com.zhaksylyk.model.User;
import com.zhaksylyk.service.UserService;
import com.zhaksylyk.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.BlockingDeque;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("test1", "test1", (byte) 1);
        userService.saveUser("test2", "test2", (byte) 2);
        userService.saveUser("test3", "test3", (byte) 3);
        userService.saveUser("test4", "test4", (byte) 4);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
