package org.pfe.cdcconfiguration.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Watcher {
    @Id
    private String name;
    private String kafka_topic;
    @OneToOne(fetch = FetchType.EAGER)
    private WatcherConfig config;
}
