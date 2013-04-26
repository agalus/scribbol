package org.scribbol.handler;

import org.scribbol.message.Command;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/25/13
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommandHandlerFactory {
    public CommandHandler getHandler(Command command);
}
