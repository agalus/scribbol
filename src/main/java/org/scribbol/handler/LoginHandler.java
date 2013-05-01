package org.scribbol.handler;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.domain.Agent;
import org.scribbol.message.MessageConstants;
import org.scribbol.message.SessionConstants;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginHandler extends AbstractCommandHandler {

    private String username;
    private String password;
    private Agent agent;

    @Override
    protected void internalHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        // Retrieve and verify the username/password
        agent = agentService.get(username);
        if(agent == null) {
            throw new CommandException("Invalid username/password.");
        }

        if(!agent.getPassword().equals(password)) {
            throw new CommandException("Invalid username/password.");
        }

        // Set Session attribute
        remote.setAttribute(SessionConstants.AUTHENTICATION_KEY, Boolean.TRUE);

        //
        addSystemMessage(remote, MessageConstants.RESP_LOGIN_KEY, MessageConstants.TRUE);
    }

    @Override
    protected void mapValues(ServerMessage.Mutable message) {
        Map<String, Object> input = message.getDataAsMap();
        username = (String)input.get(MessageConstants.USERNAME_KEY);
        password = (String)input.get(MessageConstants.PASSWORD_KEY);
        if(!StringUtils.hasText(username)) {
            throw new CommandArgumentException(MessageConstants.USERNAME_KEY,"Missing username");
        }

        if(!StringUtils.hasText(password)) {
            throw new CommandArgumentException(MessageConstants.USERNAME_KEY,"Missing password");
        }

    }
}
