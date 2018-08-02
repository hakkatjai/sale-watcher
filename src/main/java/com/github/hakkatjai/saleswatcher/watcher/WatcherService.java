package com.github.hakkatjai.saleswatcher.watcher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

}
