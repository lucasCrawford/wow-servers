package com.thorrism.controller;

import com.thorrism.entity.Server;
import com.thorrism.entity.ServerStatus;
import com.thorrism.repository.ServerRepository;
import com.thorrism.repository.ServerStatusRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Hercules on 5/7/2017.
 */
@Component
public class ServerManagerImpl implements ServerManager {

    private ServerRepository serverRepository;
    private ServerStatusRepository serverStatusRepository;

    // Lock to prevent read/write race conditions on the servers
    private final ReentrantLock lock = new ReentrantLock(true);

    private static final long LOCK_WAIT_TIMEOUT = 6000; // 6 seconds in milliseconds
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerManagerImpl.class);

    @Autowired
    public ServerManagerImpl(ServerRepository serverRepository, ServerStatusRepository serverStatusRepository) {
        this.serverRepository = serverRepository;
        this.serverStatusRepository = serverStatusRepository;
    }

    @Override
    public Iterable<Server> updateServers(Iterable<Server> servers) {
        try {
            lock.tryLock(LOCK_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
            if (servers == null) {
                return null;
            }
            return this.serverRepository.save(servers);
        } catch (InterruptedException i) {
            LOGGER.error("Error occurred finding server: " + i.getMessage(), i);
        } finally {
            lock.unlock();
        }

        // If something went wrong, return the current servers
        return this.serverRepository.findAll();
    }

    @Override
    public ServerStatus updateServerStatus(String serverUid, ServerStatus updateServerStatus) {
        try {
            lock.tryLock(LOCK_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
            Server server = findServerByUid(serverUid);
            if (server == null) {
                return null;
            }
            ServerStatus serverStatus = server.getServerStatus();
            serverStatus.setHordeStatistics(updateServerStatus.getHordeStatistics());
            serverStatus.setAllianceStatistics(updateServerStatus.getAllianceStatistics());
            serverStatus.setLastUpdate(new Date());
            return this.serverStatusRepository.save(serverStatus);
        } catch (InterruptedException i) {
            LOGGER.error("Error occurred finding server: " + i.getMessage(), i);
        } finally {
            lock.unlock();
        }

        // If something went wrong, return the current status of the server
        Server server = findServerByUid(serverUid);
        if (server == null) {
            return null;
        }
        return server.getServerStatus();
    }

    @Override
    public List<Server> findServerByName(String name) {
        return serverRepository.findServerByFriendlyNameIsStartingWithIgnoreCase(name);
    }

    @Override
    public Server findServerByUid(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        return serverRepository.findServerByUid(uid);
    }

    @Override
    public Iterable<Server> findAllServers() {
        return serverRepository.findAll();
    }
}
