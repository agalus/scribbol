package org.scribbol.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/23/13
 * Time: 7:17 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseEntity implements Serializable {
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
