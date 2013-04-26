package org.scribbol.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/7/13
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Document  extends  BaseEntity{
    protected Map<String,ScribbolObject> objectMap = new HashMap<String, ScribbolObject>();

    public void addObject(String key, ScribbolObject object) {
        objectMap.put(key, object );

    }

    public ScribbolObject getObject(String key) {
        return objectMap.get(key);

    }

}
