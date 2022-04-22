/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kehuts.module.security;

import com.kehuts.module.users.model.User;
import com.kehuts.module.users.model.dto.UserDto;
import io.smallrye.jwt.algorithm.SignatureAlgorithm;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.Instant;

/**
 * The Authorization class.
 *
 * @author patpat
 */
@Transactional
@ApplicationScoped
public class Auth {

    public Auth() {
    }

    /**
     * This method is used to login users.
     *
     * @return userdto.
     */
    public UserDto authorize(User user) {
        UserDto userDTO = UserDto.getUserDTO(user);
        try {
            var token = generateToken(userDTO);
            var refreshToken = generateRefreshToken();
            return userDTO.withToken(token, refreshToken, 3600);
        } catch (Exception ex) {
            //TODO v0.1 ensure logs are logged.
            ex.printStackTrace();
            return userDTO;
        }
    }

    /**
     * The JWT key to be issued to the user.
     *
     * @param user The current user
     * @return String representation of JWT
     */
    private String generateToken(UserDto user) {
        Instant instant = Instant.now();
        long timestampSeconds = instant.getEpochSecond();
        JwtClaimsBuilder claimsBuilder = Jwt.claims()
                //.claim("user", Collections.singletonMap("user_id", username))
                .claim("userid", user.id())
                .expiresAt(timestampSeconds + (3600*12))
                .upn("jdoe@quarkus.io")
                .audience("using-jwt-rbac")
                .preferredUserName(user.username())
                .groups(user.role());

        return claimsBuilder.subject("auth-noma-hapa")
                .issuer("https://patopato/ghsdfgdhjsdg")
                .jws()
                .header("typ", "JWT")
                .keyId("try-that-ui")
                .algorithm(SignatureAlgorithm.RS256)
                .sign();
        
    }

    /**
     * Generates a random string, saves to database and sets expiry date to 60
     * days.
     * @return String representation of refresh token
     */
    private String generateRefreshToken() {
        return Authtools.generateString();
    }

}
