package com.alientware.usermanagement.util;

import com.alientware.usermanagement.dto.MessageDto;
import java.util.ResourceBundle;

/**
 * Provides static methods to retrieve messages by ID. Also stores enums for
 * status codes.
 *
 * @author Rahul avaghan
 */
public class ResponseMessageProvider {

    /**
     * Enum for 400 Bad Requests
     */
    public enum BAD_REQUEST_400 implements ResponseMessage {

        DATE_FORMAT_INVALID("400-01"),
        INVALID_PASSWORD("400-02");
        private final int statusCode = 400;
        private final String key;

        private BAD_REQUEST_400(String keyName) {
            this.key = keyName;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public int getStatusCode() {
            return this.statusCode;
        }
    }

//  /**
//   * Enum for 404 Not Found Requests
//   */
    public enum NOT_FOUND_404 implements ResponseMessage {

        RESOURCE("404-01");

        private final int statusCode = 404;
        private final String key;

        private NOT_FOUND_404(String keyName) {
            this.key = keyName;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public int getStatusCode() {
            return this.statusCode;
        }
    }

    public enum UNAUTHORIZED_401 implements ResponseMessage {

        UNAUTHORIZED("401-01");

        private final int statusCode = 401;
        private final String key;

        private UNAUTHORIZED_401(String keyName) {
            this.key = keyName;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public int getStatusCode() {
            return this.statusCode;
        }
    }

    public enum FORBIDDEN_403 implements ResponseMessage {

        INVALID_CREDENTIAL("403-01");
        private final int statusCode = 403;

        private final String key;

        private FORBIDDEN_403(String keyName) {
            this.key = keyName;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public int getStatusCode() {
            return this.statusCode;
        }
    }

//  // Variable to store the location of the messages property files
    private static final String ressourceBaseName = "com.alientware.usermanagement.messages.messages";
//

    /**
     * This Class only implements static methods, therefore the constructor is
     * private.
     */
    private ResponseMessageProvider() {

    }

    /**
     * This method maps the id to the stored message in the messages.properties
     * file. Moreover it maps the retrieved properites to an message DTO.
     *
     * @param id the id of the message
     * @return message dto
     */
    public static MessageDto createMessageDto(String id) {

        ResourceBundle bundle = ResourceBundle.getBundle(ressourceBaseName);

        MessageDto message = new MessageDto();
        message.setMessageID(id);
        message.setMessage(bundle.getString(id));

        return message;
    }
}
