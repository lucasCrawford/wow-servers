package com.thorrism.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Hercules on 4/23/2017.
 */
@Entity
public class ServerStatus extends BaseEntity {

    @Column
    private boolean online;

    @Column
    private int population;

    @Column
    private Date lastUpdate;

    @OneToOne
    @JsonIgnore
    private Server server;

    public ServerStatus(boolean online, int population) {
        this.online = online;
        this.population = population;
    }

    public ServerStatus() {
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
