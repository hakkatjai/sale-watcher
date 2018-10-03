package com.github.hakkatjai.saleswatcher.watcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping(WatcherController.WATCHER)
@RestController
public class WatcherController {

    public static final String WATCHER = "watcher";

    private WatcherService watcherService;

    public WatcherController(WatcherService watcherService) {
        this.watcherService = watcherService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(watcherService.findAll());
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Watcher watcher) {
        Watcher persistedWatcher = watcherService.save(watcher);
        URI uri = URI.create(WATCHER + "/" + persistedWatcher.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity put(@RequestBody Watcher watcher) {
        watcherService.update(watcher);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        watcherService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
