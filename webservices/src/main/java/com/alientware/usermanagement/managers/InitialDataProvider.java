/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alientware.usermanagement.managers;

import com.alientware.usermanagement.dto.UserDto;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;

/**
 * To create some intial data on start of the application
 *
 * @author Rahul avaghan
 */
@Singleton
@Startup
public class InitialDataProvider {

    /**
     * *
     * inject manager to access CRUD operations realted to user
     */
    @Inject
    UserManager userManager;

    /**
     * *
     * add the data on start of the application
     */
    @PostConstruct
    public void start() {
        UserDto user1 = new UserDto();
        user1.setFirstName("Peter");
        user1.setLastName("Smith");
        user1.setPassword("asdf");
        user1.setPhoto("male/m (1).png");
        user1.setUserName("petersmith");
        user1.setMale(true);
        UserDto user2 = new UserDto();
        user2.setFirstName("Anna");
        user2.setLastName("Hopp");
        user2.setPassword("asdf");
        user2.setPhoto("female/f (2).png");
        user2.setUserName("annahopp");
        user1.setMale(false);

        userManager.createUser(user1);
        userManager.createUser(user2);
//        List<User> users = userManager.getAllUsers();

    }
}
