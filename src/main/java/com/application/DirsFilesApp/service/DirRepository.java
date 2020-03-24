package com.application.DirsFilesApp.service;

import com.application.DirsFilesApp.domain.Dir;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DirRepository extends CrudRepository<Dir, Integer> {
    Dir findByPathAndCreated(String path, LocalDateTime created);
    @Query("select distinct d from Dir d inner join fetch d.childDirs cd where cd.id not in (d.id)")
    List<Dir> findAllParentDirs();
}
