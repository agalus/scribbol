package org.scribbol.data;

/**
 * Created with IntelliJ IDEA.
 * User: drew
 * Date: 4/15/13
 * Time: 5:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Agent extends BaseEntity {
    protected String name;
    protected String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agent)) return false;

        Agent agent = (Agent) o;

        if (!name.equals(agent.name)) return false;
        if (!password.equals(agent.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
