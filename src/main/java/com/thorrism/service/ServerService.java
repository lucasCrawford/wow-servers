package com.thorrism.service;

import com.thorrism.entity.Server;
import com.thorrism.repository.ServerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Hercules on 4/23/2017.
 */
@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ServerService {

    private ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GET
    public List<Server> getServers(
            @QueryParam("name") @DefaultValue(StringUtils.EMPTY) String friendlyName) {
        return serverRepository.findServerByFriendlyNameIsStartingWithIgnoreCase(friendlyName);
    }

}
