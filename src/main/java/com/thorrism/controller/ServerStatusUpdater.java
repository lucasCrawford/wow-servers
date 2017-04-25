package com.thorrism.controller;

/**
 * Created by Hercules on 4/23/2017.
 */
public interface ServerStatusUpdater {

    void updateServerStatus();

    long getUpdateInterval();

    long getLastUpdate();

}
