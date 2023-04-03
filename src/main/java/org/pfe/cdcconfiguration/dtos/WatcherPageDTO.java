package org.pfe.cdcconfiguration.dtos;

import lombok.Data;
import org.pfe.cdcconfiguration.entities.Watcher;

import java.util.List;

@Data
public class WatcherPageDTO {
    private int currentPage;
    private int totalPages;
    private int totalData;
    private int pageSize;
    private List<Watcher> data;
}
