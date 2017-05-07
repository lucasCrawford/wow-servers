package com.thorrism.service;

import com.thorrism.controller.ServerManager;
import com.thorrism.entity.Realm;
import com.thorrism.entity.Server;
import com.thorrism.entity.ServerStatus;
import com.thorrism.repository.RealmRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Created by Hercules on 4/23/2017.
 */
@Path("/realm")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ServerService {

    private ServerManager serverManager;

    private RealmRepository realmRepository;

    @Autowired
    public ServerService(ServerManager serverManager, RealmRepository realmRepository) {
        this.serverManager = serverManager;
        this.realmRepository = realmRepository;
    }

    @GET
    public Iterable<Realm> getRealms() {
        return realmRepository.findAll();
    }

    @GET
    @Path("server")
    public List<Server> getServers(
            @QueryParam("name") @DefaultValue(StringUtils.EMPTY) String friendlyName) {
        return serverManager.findServerByName(friendlyName);
    }

    @GET
    @Path("server/{uid}")
    public Server getServerByUid(@PathParam("uid") String uid) {
        return serverManager.findServerByUid(uid);
    }

    @PUT
    @Path("server/{uid}/serverStatus/")
    public ServerStatus updateServerStatus(@PathParam("uid") String serverUid,
                               @RequestBody ServerStatus updatedStatus) {
        return serverManager.updateServerStatus(serverUid, updatedStatus);
    }


}
