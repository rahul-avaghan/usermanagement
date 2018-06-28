package com.alientware.usermanagement.exception;

import com.alientware.usermanagement.util.ResponseMessage;



/**
 * A custom exception indicating errors while sending a webservice response. 
 * @author Rahul avaghan
 */
public class WebserviceResponseException extends RuntimeException {
    
    private final ResponseMessage message;
    
    /**
     * Constructor, taking a responseMessage as input parameter.
     * @param responseMessage the responsemessage which caused the exception.
     */
    public WebserviceResponseException(ResponseMessage responseMessage) {
        super(responseMessage.getKey());
        this.message = responseMessage;
    }
    
    /**
     * Constructor, taking repsonseMesasge and custom error message as input.
     * @param responseMessage the responsemessage which caused the exception.
     * @param message the custom error message.
     */
    public WebserviceResponseException(ResponseMessage responseMessage, String message) {
        super(message);
        this.message = responseMessage;
    }
    

    /**
     * Returns the response message.
     * @return the response message.
     */
    public ResponseMessage getResponseMessage() {
        return message;
    }
}
