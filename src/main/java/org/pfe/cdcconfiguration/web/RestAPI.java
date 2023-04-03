package org.pfe.cdcconfiguration.web;

import lombok.AllArgsConstructor;
import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.dtos.WatcherRunSriptDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.exceptions.ConfigNotFoundException;
import org.pfe.cdcconfiguration.exceptions.ScriptArgsNotSufficientException;
import org.pfe.cdcconfiguration.exceptions.WatcherAlreadyexistsException;
import org.pfe.cdcconfiguration.services.WatcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin("*")
public class RestAPI {
    private final WatcherService watcherService;

    @GetMapping("/watcherspage")
    public ResponseEntity<WatcherPageDTO> getWatcherPage(@RequestParam(name = "page", defaultValue = "0") int pagenum, @RequestParam(name = "size", defaultValue = "5") int pagesize) {
        return ResponseEntity.ok(watcherService.pageAllWatchers(pagenum, pagesize));
    }

    @GetMapping("/watchers")
    public ResponseEntity<List<Watcher>> getWatchers() {
        return ResponseEntity.ok(watcherService.allWatchers());
    }

    @GetMapping("/watchers/{name}")
    public ResponseEntity<Optional<Watcher>> getWatchers(@PathVariable String name) {
        return ResponseEntity.ok(watcherService.getWatcherByName(name));
    }

    @PostMapping("/addwatcher")
    public ResponseEntity<?> saveConfig(@RequestBody Watcher watcher) {
        try {
            return ResponseEntity.ok(watcherService.addNewWatcher(watcher));
        } catch (ConfigNotFoundException | WatcherAlreadyexistsException | ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/runwatcher")
    public ResponseEntity<?> runWatcher(@RequestBody WatcherRunSriptDTO watcherRunSriptDTO) {
        try {
            Map<String, String> response = Map.of("name", watcherRunSriptDTO.getContainerName()
                    , "status", watcherService.runWatcherContainer(watcherRunSriptDTO));
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        } catch (ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/stopwatcher")
    public ResponseEntity<?> stopWatcher(@RequestBody String containerName) {
        try {
            Map<String, String> response = Map.of("name", containerName
                    , "status", watcherService.stopWatcherContainer(containerName));
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        } catch (ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/watcherstatus/{containerName}")
    public ResponseEntity<?> getWatcherStatus(@PathVariable String containerName) {
        try {
            Map<String, String> response = Map.of("name", containerName
                    , "status", watcherService.getWatcherContainerStatus(containerName));
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        } catch (ScriptArgsNotSufficientException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
