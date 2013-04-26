package org.scribbol.handler;

import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.message.Command;
import org.scribbol.service.AgentService;
import org.scribbol.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandHandlerFactoryImpl implements CommandHandlerFactory {
    @Inject
    protected BayeuxServer bayeux;

    @Session
    protected ServerSession serverSession;

    @Autowired
    protected AgentService agentService;

    @Autowired
    protected DocumentService documentService;

    public CommandHandler getHandler(Command command) {
        CommandHandler handler = null;
        switch(command) {
            case LOGIN:
                handler = new LoginHandler();
                break;
            case LOGOUT:
                handler = new LogoutHandler();
                break;
            case CREATE:
                handler = new CreateHandler();
                break;
            case DELETE:
                handler = new DeleteHandler();
                break;
            case LIST:
                handler = new ListHandler();
                break;
            case DRAW:
                handler = new DrawHandler();
                break;
        }

        handler.setDocumentService(documentService);
        handler.setAgentService(agentService);

        return handler;

    }
}
