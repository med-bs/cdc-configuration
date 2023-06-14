package org.pfe.cdcconfiguration.services;

import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.exceptions.ConfigNotFoundException;
import org.pfe.cdcconfiguration.exceptions.WatcherAlreadyexistsException;

import java.util.List;
import java.util.Optional;

public interface WatcherService {
    Watcher addNewWatcher(Watcher watcher) throws ConfigNotFoundException, WatcherAlreadyexistsException;
    Optional<Watcher> getWatcherByName(String connectorName);
    void deleteWatcher(String connectorName);
    List<Watcher> allWatchers();
    WatcherPageDTO pageAllWatchers(int page, int size);
    void deleteWatcher();
}
