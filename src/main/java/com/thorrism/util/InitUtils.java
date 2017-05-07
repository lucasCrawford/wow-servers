package com.thorrism.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thorrism.entity.Realm;
import com.thorrism.entity.Server;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * Created by Hercules on 4/23/2017.
 */
public final class InitUtils {

    private static final Logger LOGGER = Logger.getLogger(InitUtils.class);
    private static final String DEFAULT_SERVER_FILE = "init-data.json";

    public static Realm[] getDefaultRealms() {
        try {
            File f = new ClassPathResource(DEFAULT_SERVER_FILE).getFile();
            ObjectMapper om = new ObjectMapper();
            return om.readValue(f, Realm[].class);
        } catch (IOException ioe) {
            LOGGER.error("Failed to parse the default server file: " + DEFAULT_SERVER_FILE);
            return null;
        }
    }
}
