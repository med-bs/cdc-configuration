package org.pfe.cdcconfiguration.services;

import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.dtos.WatcherRunSriptDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.exceptions.ConfigNotFoundException;
import org.pfe.cdcconfiguration.exceptions.ScriptArgsNotSufficientException;
import org.pfe.cdcconfiguration.exceptions.WatcherAlreadyexistsException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface WatcherService {
    Watcher addNewWatcher(Watcher watcher) throws ConfigNotFoundException, WatcherAlreadyexistsException;
    Optional<Watcher> getWatcherByName(String containerName);
    List<Watcher> allWatchers();
    WatcherPageDTO pageAllWatchers(int page, int size);
    String runWatcherContainer(WatcherRunSriptDTO watcherRunSriptDTO) throws IOException, ScriptArgsNotSufficientException;
    String stopWatcherContainer(String containerName) throws IOException, ScriptArgsNotSufficientException;
    String getWatcherContainerStatus(String containerName) throws IOException, ScriptArgsNotSufficientException;
}
