package com.application.DirsFilesApp.record;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DirStatisticsRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String path;

    private LocalDateTime created;

    private Integer dirCount;

    private Integer fileCount;

    private Long totalFileSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getDirCount() {
        return dirCount;
    }

    public void setDirCount(Integer dirCount) {
        this.dirCount = dirCount;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public Long getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(Long totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
