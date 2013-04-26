package org.scribbol.message;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/22/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public enum DrawCommand {
    OBJECT_MODIFY("object:modified"),
    OBJECT_SELECT("object:selected"),
    OBJECT_MOVE("object:moving"),
    OBJECT_SCALED("object:scaling"),
    OBJECT_ROTATE("object:rotating"),
    OBJECT_CLEAR("selection:cleared"),
    SELECTION_CLEAR("selection:cleared"),
    SELECTION_CREATE("selection:created"),
    MOUSE_UP("mouse:up"),
    MOUSE_DOWN("mouse:down"),
    MOUSE_MOVE("mouse:move"),
    AFTER_RENDER("after:render"),
    PATH_CREATE("path:created"),
    OBJECT_ADD("object:added"),
    DOCUMENT_OPEN("document:open"),
    DOCUMENT_CLOSE("document:close");

    private String messageName;

    private DrawCommand(String messageName) {
        this.messageName = messageName;
    }
    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public static DrawCommand fromString(String s) {
        for(DrawCommand m: DrawCommand.values()) {
            if(m.getMessageName().equals(s))
                return m;
        }
        return null;
    }

    public String toString() {
        return getMessageName();
    }

    public static final String getKey() {
        return "drawCommand";
    }
}
