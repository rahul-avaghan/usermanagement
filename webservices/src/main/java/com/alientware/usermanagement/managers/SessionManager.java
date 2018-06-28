/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alientware.usermanagement.managers;

import com.alientware.usermanagement.entity.User;
import static com.alientware.usermanagement.util.HashHelper.generateHashForPassword;
import java.util.UUID;
import javax.inject.Inject;

/**
 *
 * @author AVG5KOR
 */
public class SessionManager {

    @Inject
    UserManager userManagement;

    public String login(String username, String password) {

        User user = userManagement.findByUserName(username);
        if (null != user && generateHashForPassword(password).equals(user.getPassword())) {
            return UUID.randomUUID().toString();
        }
        return null;

    }
}
