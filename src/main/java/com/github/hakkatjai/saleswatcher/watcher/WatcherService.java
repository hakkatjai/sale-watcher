package com.github.hakkatjai.saleswatcher.watcher;

import org.springframework.stereotype.Service;

@Service
public class WatcherService {

    private WatcherRepository watcherRepository;

    public WatcherService(WatcherRepository watcherRepository) {
        this.watcherRepository = watcherRepository;
    }

    public Iterable<Watcher> findAll() {
        return watcherRepository.findAll();
    }

    public Watcher save(Watcher watcher) {
        return watcherRepository.save(watcher);
    }

    public void update(Watcher watcher) {
        Watcher persistedWatcher = watcherRepository.findById(watcher.getId()).orElse(null);
        if (persistedWatcher != null) {
            // TODO JL map watcher values to persistedWatcher values
            watcherRepository.save(persistedWatcher);
        }
    }

    public void delete(Long id) {
        watcherRepository.deleteById(id);
    }
}
