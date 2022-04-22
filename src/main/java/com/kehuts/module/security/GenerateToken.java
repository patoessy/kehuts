/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kehuts.module.security;

/**
 *
 * @author patpat
 */
import java.util.HashMap;

import org.eclipse.microprofile.jwt.Claims;

/**
 * A simple utility class to generate and print a JWT token string to stdout.
 * Can be run with: mvn exec:java -Dexec.mainClass=org.acme.jwt.GenerateToken
 * -Dexec.classpathScope=test
 */
public class GenerateToken {

    /**
     * @param claimsJson resource for json document of claims to add; defaults
     * to "/JwtClaims.json"
     * @param duration time in seconds for expiration of generated token;
     * defaults to 300
     * @return String representation of the JWT token
     * @throws Exception
     */
    public static String generateToken(String claimsJson) throws Exception {
        //String claimsJson = "/JwtClaims.json";

        HashMap<String, Long> timeClaims = new HashMap<>();
        long duration = 3600;
        long exp = TokenUtils.currentTimeInSecs() + duration;
        timeClaims.put(Claims.exp.name(), exp);

        String token = TokenUtils.generateTokenString(claimsJson, timeClaims);
        return token;
    }
}
