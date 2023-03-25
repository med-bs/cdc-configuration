package org.pfe.cdcconfiguration.repositories;

import org.pfe.cdcconfiguration.entities.WatcherConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatcherConfigRepository extends JpaRepository<WatcherConfig, Long> {

}