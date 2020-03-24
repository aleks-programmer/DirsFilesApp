package com.application.DirsFilesApp.web.record;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class DirRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String path;

    @JsonProperty
    private LocalDateTime created;

    @JsonProperty
    private Set<DirRecord> childDirs = new HashSet<>();

    @JsonProperty
    private Set<FileRecord> childFiles = new HashSet<>();

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

    public Set<DirRecord> getChildDirs() {
        return childDirs;
    }

    public void setChildDirs(Set<DirRecord> childDirs) {
        this.childDirs = childDirs;
    }

    public Set<FileRecord> getChildFiles() {
        return childFiles;
    }

    public void setChildFiles(Set<FileRecord> childFiles) {
        this.childFiles = childFiles;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
