package org.pfe.cdcconfiguration.services;

import lombok.AllArgsConstructor;
import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.dtos.WatcherRunSriptDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.exceptions.ConfigNotFoundException;
import org.pfe.cdcconfiguration.exceptions.ScriptArgsNotSufficientException;
import org.pfe.cdcconfiguration.exceptions.WatcherAlreadyexistsException;
import org.pfe.cdcconfiguration.repositories.WatcherConfigRepository;
import org.pfe.cdcconfiguration.repositories.WatcherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        if(watcher.getConfig()==null){
            throw new ConfigNotFoundException("Configuration not found");
        }else {
            if(watcherRepository.existsById(watcher.getName())){
                throw new WatcherAlreadyexistsException(watcher.getName()+" already exists");
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
    public Optional<Watcher> getWatcherByName(String containerName){
        return watcherRepository.findById(containerName);
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

    @Override
    public String runWatcherContainer(WatcherRunSriptDTO watcherRunSriptDTO) throws IOException, ScriptArgsNotSufficientException {
        if(watcherRunSriptDTO.getContainerName().trim().isEmpty()||watcherRunSriptDTO.getTopicName().trim().isEmpty()){
            throw new ScriptArgsNotSufficientException("Argument Not Sufficient");
        }else {
            String shellFilePath = "src/main/resources/shell-script/";
            String status = getWatcherContainerStatus(watcherRunSriptDTO.getContainerName());

            if (status.equals("not found")) {
                shellFilePath += "start.sh";
            } else {
                shellFilePath += "restart.sh";
            }

            List<String> commandList = new ArrayList<>();
            commandList.add(shellFilePath);
            commandList.addAll(watcherRunSriptDTO.toArray());

            String[] command = commandList.toArray(new String[0]);
            Runtime.getRuntime().exec(command);
            return getWatcherContainerStatus(watcherRunSriptDTO.getContainerName());
        }
    }


    @Override
    public String stopWatcherContainer(String containerName) throws IOException, ScriptArgsNotSufficientException {
        if(containerName.trim().isEmpty()){
            throw new ScriptArgsNotSufficientException("Watcher Name Not Found");
        }else {
            String shellFilePath = "src/main/resources/shell-script/stop.sh";

            List<String> commandList = new ArrayList<>();
            commandList.add(shellFilePath);
            commandList.add(containerName);

            String[] command = commandList.toArray(new String[0]);
            Runtime.getRuntime().exec(command);
            return getWatcherContainerStatus(containerName);
        }
    }

    @Override
    public String getWatcherContainerStatus(String containerName) throws IOException, ScriptArgsNotSufficientException {
        if(containerName.trim().isEmpty()){
            throw new ScriptArgsNotSufficientException("Watcher Name Not Found");
        }else {
            String shellFilePath = "src/main/resources/shell-script/status.sh";

            List<String> commandList = new ArrayList<>();
            commandList.add(shellFilePath);
            commandList.add(containerName);

            String[] command = commandList.toArray(new String[0]);
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine();
        }
    }

}
