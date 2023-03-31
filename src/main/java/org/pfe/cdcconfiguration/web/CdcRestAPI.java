package org.pfe.cdcconfiguration.web;

import lombok.AllArgsConstructor;
import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.dtos.WatcherRunSriptDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.exceptions.ConfigNotFoundException;
import org.pfe.cdcconfiguration.exceptions.ScriptArgsNotSufficientException;
import org.pfe.cdcconfiguration.services.WatcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CdcRestAPI {
    WatcherService watcherService;
    @GetMapping("/allconfig")
    public ResponseEntity<WatcherPageDTO> getConfig(@RequestParam(name = "page", defaultValue = "0") int pagenum, @RequestParam(name = "page", defaultValue = "5") int pagesize){
        return ResponseEntity.ok(watcherService.pageAllWatchers(pagenum,pagesize));
    }

    @PostMapping("/addwatcher")
    public ResponseEntity<?> saveConfig(@RequestBody Watcher watcher){
        try {
            watcherService.addNewWatcher(watcher);
            return ResponseEntity.ok(watcher);
        } catch (ConfigNotFoundException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }

    @PostMapping("/runwatcher")
    public ResponseEntity<?> runWatcher(@RequestBody WatcherRunSriptDTO watcherRunSriptDTO)  {
        try {
            watcherService.runWatcherContainer(watcherRunSriptDTO);
            return ResponseEntity.ok(Map.of("ok","file running"));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error",e.getMessage()));
        } catch (ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }

    @PutMapping("/restartwatcher")
    public ResponseEntity<?> restartWatcher(@RequestBody String containerName)  {
        try {
            watcherService.restartWatcherContainer(containerName);
            return ResponseEntity.ok(Map.of("ok","file running"));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error",e.getMessage()));
        } catch (ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }

    @PutMapping("/pausewatcher")
    public ResponseEntity<?> pauseWatcher(@RequestBody String containerName)  {
        try {
            watcherService.pauseWatcherContainer(containerName);
            return ResponseEntity.ok(Map.of("ok","file running"));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error",e.getMessage()));
        } catch (ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }

    @PutMapping("/stoptwatcher")
    public ResponseEntity<?> stopWatcher(@RequestBody String containerName)  {
        try {
            watcherService.stopWatcherContainer(containerName);
            return ResponseEntity.ok(Map.of("ok","file running"));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error",e.getMessage()));
        } catch (ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }

    @GetMapping("/watcherstatus/{containerName}")
    public ResponseEntity<?> getWatcherStatus(@PathVariable String containerName)  {
        try {
            return ResponseEntity.ok(Map.of("status",watcherService.getWatcherContainerStatus(containerName)));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error",e.getMessage()));
        } catch (ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }
}
