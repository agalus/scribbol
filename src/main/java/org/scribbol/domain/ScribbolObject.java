package org.scribbol.domain;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/7/13
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScribbolObject {
    protected String SID;
    protected int angle;
    protected double scaleX;
    protected double scaleY;
    protected int top;
    protected int left;
    protected int width;
    protected int height;
    protected String SVG;

    public ScribbolObject(String id) {
        this.SID = id;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getSVG() {
        return SVG;
    }

    public void setSVG(String SVG) {
        this.SVG = SVG;
    }
}
