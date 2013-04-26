package org.scribbol.handler;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandArgumentException extends RuntimeException{
    private String argument;
    private String message;

    public CommandArgumentException(String argument, String message) {
        this.argument = argument;
        this.message = message;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
