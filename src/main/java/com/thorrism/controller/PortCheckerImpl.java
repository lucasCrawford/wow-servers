package com.thorrism.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Hercules on 4/23/2017.
 */
@Component
public class PortCheckerImpl implements PortChecker {

    private Logger LOGGER = Logger.getLogger(this.getClass());

    @Override
    public boolean isServerAndPortAlive(String server, int port) {
        if (StringUtils.isEmpty(server)) {
            LOGGER.debug("Server was provided as empty");
            return false;
        }

        try {
            new Socket(server, port).close();
            return true;
        } catch (IOException se) {
            return false;
        }
    }
}
