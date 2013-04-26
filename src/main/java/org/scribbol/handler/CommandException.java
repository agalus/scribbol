package org.scribbol.handler;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandException extends RuntimeException{
    private String message;

    public CommandException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
