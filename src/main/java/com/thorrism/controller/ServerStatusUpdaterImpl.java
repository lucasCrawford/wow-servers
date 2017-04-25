package com.thorrism.controller;

import com.thorrism.entity.Server;
import com.thorrism.entity.ServerStatus;
import com.thorrism.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Hercules on 4/23/2017.
 */
@Component
public class ServerStatusUpdaterImpl implements ServerStatusUpdater {

    private PortChecker portChecker;
    private ServerRepository serverRepository;

    @Value("${update.poll}")
    private long updatePoll;
    private long lastUpdate;

    @Autowired
    public ServerStatusUpdaterImpl(PortChecker portChecker, ServerRepository serverRepository) {
        this.portChecker = portChecker;
        this.serverRepository = serverRepository;
    }

    @Override
    @Scheduled(fixedRateString = "${update.poll}")
    public void updateServerStatus() {
        this.lastUpdate = new Date().getTime();

        // Find all the servers and perform the status update
        Iterable<Server> serverList = serverRepository.findAll();
        for (Server server : serverList) {
            ServerStatus serverStatus = server.getServerStatus();
            serverStatus.setOnline(
                    portChecker.isServerAndPortAlive(server.getIpAddress(), server.getPort())
            );
            serverStatus.setLastUpdate(new Date()); // set the last update to be exact time of update
        }

        serverRepository.save(serverList);
    }

    @Override
    public long getUpdateInterval() {
        return updatePoll;
    }

    @Override
    public long getLastUpdate() {
        return lastUpdate;
    }
}
