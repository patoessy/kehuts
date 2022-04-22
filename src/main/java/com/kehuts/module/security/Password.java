/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kehuts.module.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author patpat
 */
public class Password {

    private Password() {
    }

    public static String hashedPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static Boolean checkPassword(String plainTextPassword, String hashedPw) {
        if (BCrypt.checkpw(plainTextPassword, hashedPw)) {
            return true;
        } else {
            return false;
        }
    }
}
