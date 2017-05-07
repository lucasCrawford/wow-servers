package com.thorrism.config;

import com.thorrism.filter.AuthenticationFilter;
import com.thorrism.service.ServerService;
import org.glassfish.jersey.server.ResourceConfig;

import javax.annotation.Resource;

/**
 * Created by Hercules on 4/23/2017.
 */
@Resource
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ServerService.class);
        register(AuthenticationFilter.class);
    }
}
