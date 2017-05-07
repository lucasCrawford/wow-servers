package com.thorrism.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Hercules on 4/23/2017.
 */
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Server extends BaseEntity {

    @Column
    private String friendlyName;

    @Column
    private String ipAddress;

    @Column
    private int port;

    @Column
    private final String uid = UUID.randomUUID().toString();

    @OneToOne(cascade = CascadeType.ALL)
    private ServerStatus serverStatus;

    public Server(String friendlyName, String ipAddress,
                  int port, ServerStatus serverStatus) {
        this.friendlyName = friendlyName;
        this.ipAddress = ipAddress;
        this.port = port;

        // Attach the server to the server status.
        this.serverStatus = serverStatus;
    }

    public Server() {}

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUid() {
        return uid;
    }
}
