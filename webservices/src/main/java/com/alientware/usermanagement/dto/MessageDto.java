package com.alientware.usermanagement.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO class for transfering messages like error, info, success.
 *
 * @author Duc.Long.Nguyen
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message")
public class MessageDto {

    @NotNull
    @Size(min = 1)
    @XmlElement(required = true)
    private String messageID;

    @NotNull
    @Size(min = 1)
    @XmlElement(required = true)
    private String message;
    
    private String reason;

    /**
     * Getter for the message ID.
     *
     * @return The message ID
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * Setter for the message ID.
     *
     * @param messageID The message id
     */
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    /**
     * Getter for the message.
     *
     * @return The message as String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for the message.
     *
     * @param message The message as String
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the reason of the error message.
     * @return the reason as string.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason of the error.
     * @param reason the reason.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    
}
