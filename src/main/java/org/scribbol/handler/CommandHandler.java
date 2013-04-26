package org.scribbol.handler;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.message.SystemMessage;
import org.scribbol.service.AgentService;
import org.scribbol.service.DocumentService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommandHandler {

    public void handleMessage(ServerSession remote, ServerMessage.Mutable message);

    public AgentService getAgentService();

    public void setAgentService(AgentService agentService);

    public DocumentService getDocumentService();

    public void setDocumentService(DocumentService documentService);

    public List<SystemMessage> getSystemMessages();

    public List<ServerMessage.Mutable> getChannelMessages();


}
