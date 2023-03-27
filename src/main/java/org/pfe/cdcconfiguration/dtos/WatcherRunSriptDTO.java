package org.pfe.cdcconfiguration.dtos;

import lombok.Data;

import java.util.List;

@Data
public class WatcherRunSriptDTO {
    private String containerName;
    private String topicName;

    public  List<String> toArray(){
        return List.of(this.containerName,this.topicName);
    }
}
