package org.scribbol.handler.draw;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.handler.CommandArgumentException;
import org.scribbol.message.MessageConstants;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModifyObjectDrawCommand extends AbstractDrawCommandHandler{
    protected String objId;

    @Override
    protected void internalHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapValues(ServerMessage.Mutable message) {
        super.mapValues(message);

    }
}
