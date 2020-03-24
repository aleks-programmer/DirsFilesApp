package com.application.DirsFilesApp.web.record;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FileRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String name;

    @JsonProperty
    private LocalDateTime created;

    @JsonProperty
    private Long size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
