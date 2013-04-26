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
public class ResponseMessage {
    protected String channel;
    protected Map<String, Object> output;
    protected ServerMessage.Mutable message;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Map<String, Object> getOutput() {
        return output;
    }

    public void setOutput(Map<String, Object> output) {
        this.output = output;
    }

    public ServerMessage.Mutable getMessage() {
        return message;
    }

    public void setMessage(ServerMessage.Mutable message) {
        this.message = message;
    }
}
