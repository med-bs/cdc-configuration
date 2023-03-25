package org.pfe.cdcconfiguration.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Watcher {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String tableToListen;
    @OneToOne(fetch = FetchType.EAGER)
    private WatcherConfig config;
}
