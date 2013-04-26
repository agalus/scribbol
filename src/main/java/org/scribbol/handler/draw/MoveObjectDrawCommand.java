package org.scribbol.handler.draw;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoveObjectDrawCommand extends AbstractDrawCommandHandler{
    protected String objId;
    protected int left;
    protected int top;

    @Override
    protected void internalHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapValues(ServerMessage.Mutable message) {
        super.mapValues(message);

    }
}
