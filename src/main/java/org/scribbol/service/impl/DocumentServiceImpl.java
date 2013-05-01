package org.scribbol.service.impl;

import org.scribbol.dao.DAO;
import org.scribbol.dao.DocumentDAO;
import org.scribbol.domain.Document;
import org.scribbol.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

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

    protected Map<String, Document> documentMap = new HashMap<String, Document>();

    protected DocumentServiceImpl() {
        super(Document.class);
    }

    @Override
    protected DAO<Document, String> getDAO() {
        return documentDAO;
    }
}
