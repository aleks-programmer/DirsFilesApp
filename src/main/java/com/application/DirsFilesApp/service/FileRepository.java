package com.application.DirsFilesApp.service;

import com.application.DirsFilesApp.domain.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Integer> {
}
