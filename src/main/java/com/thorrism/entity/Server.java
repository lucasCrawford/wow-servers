package com.thorrism.entity;

import javax.persistence.*;

/**
 * Created by Hercules on 4/23/2017.
 */
@Entity
public class Server extends BaseEntity {

    @Column
    private String friendlyName;

    @Column
    private String ipAddress;

    @Column
    private int port;

    @Column
    private boolean isLoginServer;

    @OneToOne(cascade = CascadeType.ALL)
    private ServerStatus serverStatus;

    public Server(String friendlyName, String ipAddress,
                  int port, boolean isLoginServer,
                  ServerStatus serverStatus) {
        this.friendlyName = friendlyName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.isLoginServer = isLoginServer;

        // Attach the server to the server status.
        serverStatus.setServer(this);
        this.serverStatus = serverStatus;
    }

    public Server() {
    }

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

    public boolean isLoginServer() {
        return isLoginServer;
    }

    public void setLoginServer(boolean loginServer) {
        isLoginServer = loginServer;
    }
}
