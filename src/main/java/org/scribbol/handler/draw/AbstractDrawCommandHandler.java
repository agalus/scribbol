package org.scribbol.handler.draw;

import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.data.Document;
import org.scribbol.handler.AbstractCommandHandler;
import org.scribbol.handler.CommandArgumentException;
import org.scribbol.handler.CommandException;
import org.scribbol.message.MessageConstants;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDrawCommandHandler extends AbstractCommandHandler{
    protected Document document;
    protected String docId;

    @Override
    protected void preHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        super.preHandleMessage(remote, message);

        // Try opening document
        document = documentService.get(docId);

        if(document == null) {
            throw new CommandException("Unable to open docmuent: " + docId);
        }
    }

    @Override
    protected void postHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        super.postHandleMessage(remote, message);

        // Try saving document
        document = documentService.update(document);

        // Notify all subscribers of the change
        addChannelMessage(message);
    }

    @Override
    protected void mapValues(ServerMessage.Mutable message) {
        Map<String, Object> input = message.getDataAsMap();
        docId = (String)input.get(MessageConstants.DRAW_DOCUMENT_ID);

        if(!StringUtils.hasText(docId)) {
            throw new CommandArgumentException(MessageConstants.DRAW_DOCUMENT_ID,"Missing docId.");
        }

    }

}
