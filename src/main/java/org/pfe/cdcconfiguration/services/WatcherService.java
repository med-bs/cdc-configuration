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
    void runScript(WatcherRunSriptDTO watcherRunSriptDTO) throws IOException, ScriptArgsNotSufficientException;
    void restartScript(String containerName) throws IOException, ScriptArgsNotSufficientException;
}
