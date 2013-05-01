/*
 * Copyright (c) 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scribbol.service;

import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.handler.CommandArgumentException;
import org.scribbol.handler.CommandException;
import org.scribbol.handler.CommandHandler;
import org.scribbol.handler.CommandHandlerFactory;
import org.scribbol.message.Command;
import org.scribbol.message.MessageConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Named
@Singleton
@Service("messageService")
public class MessageService
{
    private final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Inject
    private BayeuxServer bayeux;
    @Session
    private ServerSession serverSession;

    @Autowired
    private AgentService agentService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CommandHandlerFactory commandHandlerFactory;

    @PostConstruct
    public void init()
    {
    }

    @Listener("/scribbol")
    public void processMessage(ServerSession remote, ServerMessage.Mutable message)
    {
        Map<String, Object> input = message.getDataAsMap();
        String commandString = (String)input.get(MessageConstants.COMMAND_KEY);
        Command command = Command.fromString(commandString);

        // Parse the command
        if(command == null) {
            logger.error("Invalid command string: {} from: {} data: ", commandString, remote.toString(), input.toString());
            sendErrorMessage(remote, "Invalid command string: " + commandString);
            return;
        }

        // Get the command handler
        CommandHandler handler = commandHandlerFactory.getHandler(command);

        // Execute the handler
        try {
            handler.handleMessage(remote, message);
        }
        catch (CommandArgumentException ex) {
            logger.error("Command argument error: {} {}",ex.getArgument(), ex.getMessage());
            sendErrorMessage(remote, "Argument exception " + ex.getArgument() + " : " + ex.getMessage());
            return;
        }
        catch (CommandException ex) {
            logger.error("Error: " + ex.getMessage(), ex);
            sendErrorMessage(remote, "Error: " + ex.getMessage());
            return;
        }
        catch(Exception ex) {
            logger.error("Unexpected error occurred from: {} values: {}",remote.toString(), message.toString(), ex);
            sendErrorMessage(remote, "Unexpected error occurred.");
            return;
        }

//        // Send out system messages
//        for(SystemMessage systemMessage : handler.getSystemMessages())
//            sendSystemMessage(remote, systemMessage.getKey(), systemMessage.getMessage());
//
//        // Send out channel messages
//        for(ServerMessage.Mutable channelMessage : handler.getChannelMessages())
//            sendChannelMessage(remote, channelMessage, false);

    }

    protected void sendChannelMessage(ServerSession remote, ServerMessage.Mutable message, boolean includeRemote) {
        String channelName = message.getChannel();
        ServerChannel channel = bayeux.getChannel(channelName);
        //channel.publish(serverSession,message);
        Set<ServerSession> subscribers = channel.getSubscribers();
        for(ServerSession subscriber : subscribers) {
            if(!includeRemote && (subscriber == remote))
                continue;
            subscriber.deliver(remote, message);
        }
    }

    protected void sendSystemMessage(ServerSession remote, String key, String message) {
        Map<String, Object> output = new HashMap<String, Object>();
        output.put(key, message);
        remote.deliver(serverSession, "/message", output, null);
    }

    protected void sendErrorMessage(ServerSession remote, String error) {
        Map<String, Object> output = new HashMap<String, Object>();
        output.put(MessageConstants.ERROR_KEY, error);
        remote.deliver(serverSession, "/message", output, null);
    }
}
