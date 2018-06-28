/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alientware.usermanagement.util;

import com.alientware.usermanagement.exception.WebserviceResponseException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Rahul avaghan
 */
public class HashHelper {

    /**
     * *
     * to hash the user provided password
     *
     * @param passwordToHash password to hash
     * @return it returns md5 hash
     */
    public static String generateHashForPassword(String passwordToHash) {

        try {
            if (null != passwordToHash && !passwordToHash.trim().equals("")) {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(passwordToHash.getBytes());
                //Get the hash's bytes 
                byte[] bytes = md.digest();
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                return sb.toString();
            }
            throw new WebserviceResponseException(ResponseMessageProvider.BAD_REQUEST_400.INVALID_PASSWORD);
        } catch (NoSuchAlgorithmException e) {
            throw new WebserviceResponseException(ResponseMessageProvider.NOT_FOUND_404.RESOURCE);
        }
    }
}
