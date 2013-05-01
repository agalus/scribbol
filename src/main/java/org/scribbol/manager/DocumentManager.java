package org.scribbol.manager;

import org.scribbol.domain.Document;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/15/13
 * Time: 7:02 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DocumentManager<S> {
    public Collection<S> getSessions(Document doc);
    public void addDocument(Document doc);
    public void removeDocument(Document doc);
    public void openDocument(Document doc, S s);
    public void closeDocument(Document doc, S s);


}
