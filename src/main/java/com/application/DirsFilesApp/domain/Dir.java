package com.application.DirsFilesApp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dir")
public class Dir implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column(updatable = false, nullable = false)
    private String path;

    @Column(updatable = false, nullable = false)
    private LocalDateTime created;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dir_in_dir",
            joinColumns = @JoinColumn(name = "parent_dir_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dir_id", referencedColumnName = "id")
    )
    private Set<Dir> childDirs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "file_in_dir",
            joinColumns = @JoinColumn(name = "parent_dir_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id")
    )
    private Set<File> childFiles = new HashSet<>();

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

    public Set<Dir> getChildDirs() {
        return childDirs;
    }

    public void setChildDirs(Set<Dir> childDirs) {
        this.childDirs = childDirs;
    }

    public Set<File> getChildFiles() {
        return childFiles;
    }

    public void setChildFiles(Set<File> childFiles) {
        this.childFiles = childFiles;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
