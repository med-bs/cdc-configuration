package org.pfe.cdcconfiguration.services;

import lombok.AllArgsConstructor;
import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.repositories.WatcherConfigRepository;
import org.pfe.cdcconfiguration.repositories.WatcherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WatcherServiceImpl implements WatcherService {
    WatcherRepository watcherRepository;
    WatcherConfigRepository watcherConfigRepository;
    @Override
    public Watcher addNewWatcher(Watcher watcher) {
        watcherConfigRepository.save(watcher.getConfig());
        return watcherRepository.save(watcher);
    }

    @Override
    public List<Watcher> allWatchers() {
        return watcherRepository.findAll();
    }

    @Override
    public WatcherPageDTO pageAllWatchers(int page, int size) {
        WatcherPageDTO watcherPageDTO = new WatcherPageDTO();
        Page<Watcher> watcherPage = watcherRepository.findAll(PageRequest.of(page,size));

        watcherPageDTO.setWatchers(watcherPage.getContent());
        watcherPageDTO.setCurrentPage(page);
        watcherPageDTO.setPageSize(size);
        watcherPageDTO.setTotalPages(watcherPage.getTotalPages());

        return watcherPageDTO;
    }
}
