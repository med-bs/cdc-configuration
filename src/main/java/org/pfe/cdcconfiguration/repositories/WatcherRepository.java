package org.pfe.cdcconfiguration.repositories;

import org.pfe.cdcconfiguration.entities.Watcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatcherRepository extends JpaRepository<Watcher, String> {
    @Override
    boolean existsById(String s);
}