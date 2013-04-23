package org.scribbol.manager;

import org.scribbol.data.Document;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/15/13
 * Time: 7:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentManagerImpl<S> implements DocumentManager<S>{
    private Map<String, Document> documents = new HashMap<String, Document>();

    public Collection<S> getSessions(Document doc) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addDocument(Document doc) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeDocument(Document doc) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void openDocument(Document doc, S s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void closeDocument(Document doc, S s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
