package org.pfe.cdcconfiguration.services;

import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.entities.Watcher;

import java.util.List;

public interface WatcherService {
    Watcher addNewWatcher(Watcher watcher);
    List<Watcher> allWatchers();
    WatcherPageDTO pageAllWatchers(int page, int size);
}
