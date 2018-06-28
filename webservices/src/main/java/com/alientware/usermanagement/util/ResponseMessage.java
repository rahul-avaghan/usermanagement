package com.alientware.usermanagement.util;

/**
 * An Interface to define a message.
 * @author Rahul avaghan
 */
public interface ResponseMessage {
    
    /**
     * @return the status code (e.g. 200, 400, 404)
     */
    public int getStatusCode();
    
    /**
     * @return the key value of the message
     */
    public String getKey();
}
