package com.thorrism.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Hercules on 5/7/2017.
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Realm extends BaseEntity {

    @Column
    private String name;

    @Column
    private String realmList;

    @OneToOne(cascade = CascadeType.ALL)
    private Server loginServer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Server> servers;

    public Realm(String name, String realmList, Server loginServer, List<Server> servers) {
        this.name = name;
        this.realmList = realmList;
        this.loginServer = loginServer;
        this.servers = servers;
    }

    public Realm(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealmList() {
        return realmList;
    }

    public void setRealmList(String realmList) {
        this.realmList = realmList;
    }

    public Server getLoginServer() {
        return loginServer;
    }

    public void setLoginServer(Server loginServer) {
        this.loginServer = loginServer;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
