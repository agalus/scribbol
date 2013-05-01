package org.scribbol.dao.impl;

import org.scribbol.dao.DocumentDAO;
import org.scribbol.domain.Document;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/15/13
 * Time: 5:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentDAOImpl extends AbstractInMemoryDAO<Document> implements DocumentDAO {

    protected DocumentDAOImpl() {
        super(Document.class);
        Document defaultDoc = new Document();
        defaultDoc.setId("default");
        this.create(defaultDoc);
    }
}
