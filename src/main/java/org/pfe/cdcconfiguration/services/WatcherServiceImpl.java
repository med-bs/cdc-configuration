package org.pfe.cdcconfiguration.services;

import lombok.AllArgsConstructor;
import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.exceptions.ConfigNotFoundException;
import org.pfe.cdcconfiguration.exceptions.WatcherAlreadyexistsException;
import org.pfe.cdcconfiguration.repositories.WatcherConfigRepository;
import org.pfe.cdcconfiguration.repositories.WatcherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class WatcherServiceImpl implements WatcherService {
    WatcherRepository watcherRepository;
    WatcherConfigRepository watcherConfigRepository;


    @Override
    public Watcher addNewWatcher(Watcher watcher) throws ConfigNotFoundException, WatcherAlreadyexistsException {
        if (watcher.getConfig() == null) {
            throw new ConfigNotFoundException("Configuration not found");
        } else {
            if (watcherRepository.existsById(watcher.getName())) {
                throw new WatcherAlreadyexistsException(watcher.getName() + " already exists");
            }

            watcherConfigRepository.save(watcher.getConfig());
            return watcherRepository.save(watcher);
        }
    }

    @Override
    public List<Watcher> allWatchers() {
        return watcherRepository.findAll();
    }

    @Override
    public Optional<Watcher> getWatcherByName(String containerName) {
        return watcherRepository.findById(containerName);
    }

    @Override
    public void deleteWatcher(String connectorName) {
        Optional<Watcher> w = watcherRepository.findById(connectorName);
        watcherRepository.delete(w.get());
    }

    @Override
    public WatcherPageDTO pageAllWatchers(int page, int size) {
        WatcherPageDTO watcherPageDTO = new WatcherPageDTO();
        Page<Watcher> watcherPage = watcherRepository.findAll(PageRequest.of(page, size));

        watcherPageDTO.setData(watcherPage.getContent());
        watcherPageDTO.setCurrentPage(page);
        watcherPageDTO.setPageSize(size);
        watcherPageDTO.setTotalData(watcherPage.getNumberOfElements());
        watcherPageDTO.setTotalPages(watcherPage.getTotalPages());

        return watcherPageDTO;
    }

    @Override
    public void deleteWatcher() {

    }

}
