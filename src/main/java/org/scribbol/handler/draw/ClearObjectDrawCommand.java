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
public class ClearObjectDrawCommand extends AbstractDrawCommandHandler{
    protected String[] ids;
    @Override
    protected void internalHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void mapValues(ServerMessage.Mutable message) {
        super.mapValues(message);
        Map<String, Object> input = message.getDataAsMap();

        // Get the ids
        String idInput = (String)input.get(MessageConstants.DRAW_DELETE_IDS);
        if(!StringUtils.hasText(idInput)) {
            throw new CommandArgumentException(MessageConstants.DRAW_DELETE_IDS, "Missing ids.");
        }

        // Split the ids
        try {
            ids = idInput.split(",");
        } catch(Exception ex) {
            throw new CommandArgumentException(MessageConstants.DRAW_DELETE_IDS, "Not formatted correctly.");
        }

    }
}
