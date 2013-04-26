package org.scribbol.handler;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.handler.draw.*;
import org.scribbol.message.DrawCommand;
import org.scribbol.message.MessageConstants;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrawHandler extends AbstractCommandHandler {
    protected DrawCommand drawCommand;

    @Override
    protected void internalHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        AbstractDrawCommandHandler drawCommandHandler = null;
        switch(drawCommand) {
            case OBJECT_CLEAR:
                drawCommandHandler = new ClearObjectDrawCommand();
                break;
            case OBJECT_ADD:
                drawCommandHandler = new CreateObjectDrawCommand();
                break;
            case PATH_CREATE:
                drawCommandHandler = new CreatePathDrawCommand();
                break;
            case OBJECT_MOVE:
                drawCommandHandler = new MoveObjectDrawCommand();
                break;
            case OBJECT_ROTATE:
                drawCommandHandler = new RotateObjectDrawCommand();
                break;
            case OBJECT_SCALED:
                drawCommandHandler = new ScaleObjectDrawCommand();
                break;
        }

        if(drawCommandHandler == null) {
            throw new CommandException("Draw command not yet support: " + drawCommand);
        }

        drawCommandHandler.setDocumentService(documentService);
        drawCommandHandler.setAgentService(agentService);

        drawCommandHandler.handleMessage(remote, message);

        systemMessages.addAll(drawCommandHandler.getSystemMessages());
        channelMessages.addAll(drawCommandHandler.getChannelMessages());
    }

    @Override
    protected void mapValues(ServerMessage.Mutable message) {
        Map<String, Object> input = message.getDataAsMap();
        String drawCommandString = (String)input.get(MessageConstants.DRAW_COMMAND_KEY);
        drawCommand = DrawCommand.fromString(drawCommandString);
        if(drawCommand == null) {
            throw new CommandArgumentException(MessageConstants.DRAW_COMMAND_KEY, "Invalid draw command: " + drawCommandString);
        }
    }
}
