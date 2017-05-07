package com.thorrism.controller;

import com.thorrism.entity.Server;
import com.thorrism.entity.ServerStatus;

import java.util.List;

/**
 * Created by Hercules on 5/7/2017.
 */
public interface ServerManager {

    /**
     * Update (or created) servers within the database
     *
     * <p>
     *     Note: This method's implementation should consider
     *     race conditions
     * </p>
     *
     * @param server  servers being updated
     * @return  servers after they have been persisted.
     */
    Iterable<Server> updateServers(Iterable<Server> server);

    ServerStatus updateServerStatus(String serverUid, ServerStatus serverStatus);

    List<Server> findServerByName(String name);

    Server findServerByUid(String uid);

    Iterable<Server> findAllServers();
}
