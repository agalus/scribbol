package org.scribbol.domain;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/7/13
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Path extends ScribbolObject {
    protected String path;
    protected String svg;

    public Path(String id) {
        super(id);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
}
