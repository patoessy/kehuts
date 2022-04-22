/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kehuts.module.users.service;

import com.kehuts.module.security.Auth;
import com.kehuts.module.security.Password;
import com.kehuts.module.users.model.User;
import com.kehuts.module.users.model.dto.UserDto;
import com.kehuts.module.users.model.dto.UserInputModel;
import com.kehuts.module.users.repository.UserRepository;
import com.kehuts.module.utils.UserType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author patrick
 */
@Transactional
@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Inject
    Auth auth;

    private User loginUser(UserInputModel userModel) {
        Optional<User> user = Optional.ofNullable(userRepository.getByEmail(userModel.email()));
        if (user.isPresent()) {
            User userToVerify = user.get();

            if (Password.checkPassword(userModel.password(), userToVerify.getPassword())) {
                return userToVerify;
            }
        }

        return null; //TODO eliminate NULLS
    }

    @Transactional
    public UserDto authoriseUser(UserInputModel userModel){
        User user = loginUser(userModel);
        if(user==null) {
            return null;
        }
        UserDto authorizedUser = auth.authorize(user);
        userRepository.addUserRefreshToken(authorizedUser);
        return authorizedUser;
    }

    @Transactional
    public void createUser(UserInputModel inputModel, UserType tenant) {
        User user = new User();
        user.setEmail(inputModel.email());
        user.setPassword(Password.hashedPassword(inputModel.password()));
        user.setRole(tenant);
        user.setFullnames(inputModel.fullname());
        user.setPhonenumber(inputModel.phoneNumber());
        userRepository.persist(user);
    }
}