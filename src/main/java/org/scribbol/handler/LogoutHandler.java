package org.scribbol.handler;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogoutHandler extends AbstractCommandHandler {
    @Override
    protected void internalHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapValues(ServerMessage.Mutable message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
