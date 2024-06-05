package com.zhaksylyk;

import com.zhaksylyk.service.UserService;
import com.zhaksylyk.service.UserServiceImpl;
import com.zhaksylyk.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
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
