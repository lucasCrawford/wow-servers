package com.thorrism.controller;

/**
 * Created by Hercules on 4/23/2017.
 */
public interface PortChecker {

    boolean isServerAndPortAlive(String server, int port);
}
