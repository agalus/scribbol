package org.scribbol.message;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/22/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Command {
    LOGIN("login"),
    LOGOUT("logout"),
    LIST("list"),
    OPEN("open"),
    CREATE("create"),
    DELETE("delete"),
    DRAW("draw");



    private String messageName;

    private Command(String messageName) {
        this.messageName = messageName;
    }
    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public Command fromString(String s) {
        for(Command m: Command.values()) {
            if(m.getMessageName().equals(s))
                return m;
        }
        return null;
    }

    public String toString() {
        return getMessageName();
    }
}
