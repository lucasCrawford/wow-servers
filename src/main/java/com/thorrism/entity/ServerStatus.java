package com.thorrism.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Hercules on 4/23/2017.
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServerStatus extends BaseEntity {

    @Column
    private boolean online;

    @Column
    private String hordeStatistics;

    @Column
    private String allianceStatistics;

    @Column
    private Date lastUpdate;

    public ServerStatus(boolean online) {
        this.online = online;
    }

    public ServerStatus() {
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getHordeStatistics() {
        return hordeStatistics;
    }

    public void setHordeStatistics(String hordeStatistics) {
        this.hordeStatistics = hordeStatistics;
    }

    public String getAllianceStatistics() {
        return allianceStatistics;
    }

    public void setAllianceStatistics(String allianceStatistics) {
        this.allianceStatistics = allianceStatistics;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
