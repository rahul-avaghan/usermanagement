/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alientware.usermanagement.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

/**
 *
 * @author avg5kor
 */
public class JWTTokenMAnager {

    //Sample method to construct a JWT
    private String createJWT(String subject) {

        Key key = MacProvider.generateKey();

        String compactJws = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return null;
    }
}
