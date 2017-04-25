package com.thorrism;

import com.thorrism.controller.ServerStatusUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Startup listener to run some initialization steps, as soon as the application
 * is fired up and ready on the web server.
 *
 * Created by Hercules on 4/23/2017.
 */
@Component
public class WowServersStartup implements ApplicationListener<ApplicationReadyEvent> {

    private ServerStatusUpdater serverStatusUpdater;

    @Autowired
    public WowServersStartup(ServerStatusUpdater serverStatusUpdater) {
        this.serverStatusUpdater = serverStatusUpdater;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        // Immediately run the server update
        serverStatusUpdater.updateServerStatus();
    }
}
