package com.application.DirsFilesApp.service;

import com.application.DirsFilesApp.domain.Dir;
import com.application.DirsFilesApp.exception.DirNotFoundException;
import com.application.DirsFilesApp.record.DirFileStatisticsRecord;
import com.application.DirsFilesApp.record.DirStatisticsRecord;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface DirService {
    Dir findByDirPathAndCreated(String path, LocalDateTime created);
    List<DirFileStatisticsRecord> getFiles(Integer dirId) throws DirNotFoundException;
    Dir save(String path) throws DirNotFoundException, IOException;
    DirStatisticsRecord addToList(String path) throws DirNotFoundException, IOException;
    List<DirStatisticsRecord> getDirs();
    List<DirFileStatisticsRecord> sortByDirFileName(List<DirFileStatisticsRecord> records);
}
