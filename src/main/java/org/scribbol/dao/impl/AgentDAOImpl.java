package org.scribbol.dao.impl;

import org.scribbol.dao.AgentDAO;
import org.scribbol.domain.Agent;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/15/13
 * Time: 5:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class AgentDAOImpl extends AbstractInMemoryDAO<Agent> implements AgentDAO {

    protected AgentDAOImpl() {
        super(Agent.class);
    }
}
