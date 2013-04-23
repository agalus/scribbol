package org.scribbol.service.impl;

import org.scribbol.dao.DAO;
import org.scribbol.dao.DocumentDAO;
import org.scribbol.data.Document;
import org.scribbol.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/23/13
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentServiceImpl extends AbstractCrudService<Document, String> implements DocumentService {

    @Autowired
    protected DocumentDAO documentDAO;

    protected DocumentServiceImpl() {
        super(Document.class);
    }

    @Override
    protected DAO<Document, String> getDAO() {
        return documentDAO;
    }
}
