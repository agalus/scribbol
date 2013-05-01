package org.scribbol.handler.draw;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.scribbol.domain.Path;
import org.scribbol.handler.CommandArgumentException;
import org.scribbol.message.MessageConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreatePathDrawCommand extends AbstractDrawCommandHandler{
    private final Logger logger = LoggerFactory.getLogger(CreatePathDrawCommand.class);

    protected String objId;
    protected String svg;
    protected String path;
    protected int left;
    protected int top;
    protected int width;
    protected int height;

    @Override
    protected void internalHandleMessage(ServerSession remote, ServerMessage.Mutable message) {
        Path newObject = new Path(objId);
        newObject.setAngle(0);
        newObject.setLeft(left);
        newObject.setTop(top);
        newObject.setWidth(width);
        newObject.setHeight(height);
        newObject.setSvg(svg);

        document.addObject(objId, newObject);


    }

    @Override
    protected void mapValues(ServerMessage.Mutable message) {
        super.mapValues(message);
        Map<String, Object> input = message.getDataAsMap();

        objId = (String)input.get(MessageConstants.DRAW_OBJECT_ID);
        if(!StringUtils.hasText(objId)) {
            throw new CommandArgumentException(MessageConstants.DRAW_OBJECT_ID,"Invalid object id");
        }

        try {
            svg = (String)input.get(MessageConstants.DRAW_OBJECT_SVG);
            path = (String)input.get(MessageConstants.DRAW_OBJECT_PATH);
            left = ((Number)input.get(MessageConstants.DRAW_TRANSFORM_LEFT)).intValue();
            top = ((Number)input.get(MessageConstants.DRAW_TRANSFORM_TOP)).intValue();
            width = ((Number)input.get(MessageConstants.DRAW_TRANSFORM_WIDTH)).intValue();
            height = ((Number)input.get(MessageConstants.DRAW_TRANSFORM_HEIGHT)).intValue();
        } catch(Exception ex) {
            logger.error("Error parsing path argument.", ex);
            throw new CommandArgumentException("svg,left,top,width,height", "required command args");
        }

    }
}
