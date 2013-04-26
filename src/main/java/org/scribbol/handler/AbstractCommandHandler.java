package org.scribbol.handler;

import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.message.SystemMessage;
import org.scribbol.service.AgentService;
import org.scribbol.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCommandHandler implements CommandHandler {

//    protected BayeuxServer bayeux;
//
//    protected ServerSession serverSession;

    protected AgentService agentService;

    protected DocumentService documentService;

    protected List<SystemMessage> systemMessages = new ArrayList<SystemMessage>();

    protected List<ServerMessage.Mutable> channelMessages = new ArrayList<ServerMessage.Mutable>();

    protected void preHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        // Do nothing
    }

    protected void postHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        // Do nothing
    }

    public final void handleMessage(ServerSession remote, ServerMessage.Mutable message) {
        // Map internal values
        mapValues(message);

        // Pre-handle Message
        preHandleMessage(remote, message);

        // Internal impl of handle
        internalHandleMessage(remote,message);

        // Post-handle impl
        postHandleMessage(remote,message);

    }
    protected abstract void internalHandleMessage(ServerSession remote, ServerMessage.Mutable message);

    protected abstract void mapValues(ServerMessage.Mutable message);

    protected void addSystemMessage(ServerSession remote, String key, String message) {
        SystemMessage systemMessage = new SystemMessage(key,message);
        systemMessages.add(systemMessage);
    }

    protected void addChannelMessage(ServerMessage.Mutable channelMessage) {
        channelMessages.add(channelMessage);

    }

    public AgentService getAgentService() {
        return agentService;
    }

    public void setAgentService(AgentService agentService) {
        this.agentService = agentService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public List<SystemMessage> getSystemMessages() {
        return systemMessages;
    }

    public List<ServerMessage.Mutable> getChannelMessages() {
        return channelMessages;
    }
}
