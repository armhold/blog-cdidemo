package net.ftlines.blog.cdidemo.model;

import java.io.Serializable;
import java.util.Date;

public class EmployeeCriteria implements Serializable {
    private Date hireDateMin;
    private Date hireDateMax;
    private Team team;

    public Date getHireDateMin() {
        return hireDateMin;
    }

    public void setHireDateMin(Date hireDateMin) {
        this.hireDateMin = hireDateMin;
    }

    public Date getHireDateMax() {
        return hireDateMax;
    }

    public void setHireDateMax(Date hireDateMax) {
        this.hireDateMax = hireDateMax;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
