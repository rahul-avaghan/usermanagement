/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alientware.usermanagement.managers;

import com.alientware.usermanagement.dto.UserDto;
import com.alientware.usermanagement.entity.User;
import com.alientware.usermanagement.util.CrudService;
import static com.alientware.usermanagement.util.HashHelper.generateHashForPassword;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

/**
 * to manage user crud operations
 *
 * @author avg5kor
 */
@RequestScoped
public class UserManager {

    Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
    /**
     * *
     * persistance context
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * *
     * generalised user jpa accessing object
     */
    @Inject
    CrudService<User> userRepository;

    /**
     * *
     * get all existing users
     *
     * @return list of users
     */
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll(User.class);
        List<UserDto> userDtos = new ArrayList<>();
        users.stream().forEach(t -> userDtos.add(mapToUserDto(t)));
        return userDtos;
    }

    /**
     * *
     * To create new user
     *
     * @param userDto user data transfer object
     * @return return the user created
     */
    public UserDto createUser(UserDto userDto) {
        if (!validateUser(userDto)) {
            return null;
        }
        User user = mapper.map(userDto, User.class);
        user.setId(null);
        user.setPassword(generateHashForPassword(userDto.getPassword()));
        userRepository.create(user);
        return mapToUserDto(user);
    }

    /**
     * *
     * to update user info
     *
     * @param userDto
     * @return return the updated user info
     */
    public User updateUser(UserDto userDto) {
        if (null != userDto.getId() && validateUserForUpdate(userDto)) {
            return userRepository.update(handlePassword(mapper.map(userDto, User.class)));
        }
        return null;
    }

    /**
     * *
     *
     * @param id id of the user to be deleted
     */
    public void deleteUser(Long id) {
        User user;
        if (null != id && !id.equals(0L)) {
            user = userRepository.find(User.class, id);
            if (null != user) {
                userRepository.delete(user);
            }
        }

    }

    /**
     * *
     * find user by name
     *
     * @param userName
     * @return
     */
    public User findByUserName(String userName) {

        StringBuilder builder = new StringBuilder();
        builder.append("select u from User as u where LOWER(u.userName)=LOWER(").append(userName).append(")");
        List<User> users = em.createQuery(builder.toString()).getResultList();

        if (null != users && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    /**
     * *
     *
     * @param id id of the user to be found
     * @return returns user info
     */
    public UserDto findById(long id) {

        if (id != 0) {
            return mapToUserDto(userRepository.find(User.class, id));
        }
        return null;
    }

    /**
     * *
     * map user entity object to dto
     *
     * @param user
     * @return
     */
    private UserDto mapToUserDto(User user) {

        UserDto userDto = mapper.map(user, UserDto.class);
        userDto.setPassword(null);//remove pass word info
        return userDto;
    }

    /**
     * *
     * handle password
     *
     * @param newUser
     * @return
     */
    private User handlePassword(User newUser) {
        User oldUser;
        if (null == newUser.getPassword()) {
            oldUser = userRepository.find(User.class, newUser.getId());
            newUser.setPassword(oldUser.getPassword());
        } else {
            newUser.setPassword(generateHashForPassword(newUser.getPassword()));
        }
        return newUser;
    }

    /**
     * *
     * validate userinformation for update
     *
     * @param userDto
     * @return
     */
    private Boolean validateUserForUpdate(UserDto userDto) {
//        User user = findByUserName(userDto.getUserName());
//        if (null != user && !user.getId().equals(userDto.getId())) {
//            return Boolean.FALSE;
//        }
        return Boolean.TRUE;
    }

    /**
     * *
     * check for existance of a user
     *
     * @param userDto user information client
     * @return
     */
    private Boolean validateUser(UserDto userDto) {
//        if (null != userDto.getUserName() && null != findByUserName(userDto.getUserName())) {
//            return Boolean.FALSE;
//        }
        return Boolean.TRUE;
    }
}
