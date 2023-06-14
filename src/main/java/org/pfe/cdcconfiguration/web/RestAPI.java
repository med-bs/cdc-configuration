package org.pfe.cdcconfiguration.web;

import lombok.AllArgsConstructor;
import org.pfe.cdcconfiguration.dtos.WatcherPageDTO;
import org.pfe.cdcconfiguration.entities.Watcher;
import org.pfe.cdcconfiguration.exceptions.ConfigNotFoundException;
import org.pfe.cdcconfiguration.exceptions.WatcherAlreadyexistsException;
import org.pfe.cdcconfiguration.services.WatcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/watchers/{name}")
    public ResponseEntity<?> deleteWatchers(@PathVariable String name) {
        try {
            watcherService.deleteWatcher(name);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addwatcher")
    public ResponseEntity<?> saveConfig(@RequestBody Watcher watcher) {
        try {
            return ResponseEntity.ok(watcherService.addNewWatcher(watcher));
        } catch (ConfigNotFoundException | WatcherAlreadyexistsException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
