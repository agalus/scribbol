package org.scribbol.message;

import org.cometd.bayeux.server.ServerMessage;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/26/13
 * Time: 1:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class SystemMessage {
    protected String key;
    protected String message;

    public SystemMessage(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
