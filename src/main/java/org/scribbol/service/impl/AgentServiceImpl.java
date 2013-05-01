package org.scribbol.service.impl;

import org.scribbol.dao.AgentDAO;
import org.scribbol.dao.DAO;
import org.scribbol.domain.Agent;
import org.scribbol.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/23/13
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class AgentServiceImpl extends AbstractCrudService<Agent, String> implements AgentService {

    @Autowired
    protected AgentDAO agentDAO;

    protected AgentServiceImpl() {
        super(Agent.class);
    }

    @Override
    protected DAO<Agent, String> getDAO() {
        return agentDAO;
    }
}
