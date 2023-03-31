package org.pfe.cdcconfiguration.services;

import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.dtos.WatcherRunSriptDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.exceptions.ConfigNotFoundException;
import org.pfe.cdcconfiguration.exceptions.ScriptArgsNotSufficientException;

import java.io.IOException;
import java.util.List;

public interface WatcherService {
    Watcher addNewWatcher(Watcher watcher) throws ConfigNotFoundException;
    List<Watcher> allWatchers();
    WatcherPageDTO pageAllWatchers(int page, int size);
    void runWatcherContainer(WatcherRunSriptDTO watcherRunSriptDTO) throws IOException, ScriptArgsNotSufficientException;
    void restartWatcherContainer(String containerName) throws IOException, ScriptArgsNotSufficientException;
    void pauseWatcherContainer(String containerName) throws IOException, ScriptArgsNotSufficientException;
    void stopWatcherContainer(String containerName) throws IOException, ScriptArgsNotSufficientException;
    String getWatcherContainerStatus(String containerName) throws IOException, ScriptArgsNotSufficientException;
}
