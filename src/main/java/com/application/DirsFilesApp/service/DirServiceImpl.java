package com.application.DirsFilesApp.service;

import com.application.DirsFilesApp.domain.Dir;
import com.application.DirsFilesApp.domain.File;
import com.application.DirsFilesApp.exception.DirNotFoundException;
import com.application.DirsFilesApp.record.DirFileStatisticsRecord;
import com.application.DirsFilesApp.record.DirStatisticsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DirServiceImpl implements DirService {

    @Autowired
    private DirRepository dirRepository;

    @Autowired
    private DirService dirService;

    @Override
    @Transactional
    public Dir findByDirPathAndCreated(String path, LocalDateTime created) {
        return dirRepository.findByPathAndCreated(path, created);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DirFileStatisticsRecord> getFiles(Integer dirId) throws DirNotFoundException {
        List<DirFileStatisticsRecord> result = new ArrayList<>();

        Dir dir = dirRepository.findById(dirId).orElseThrow(DirNotFoundException::new);
        for (Dir childDir : dir.getChildDirs()) {
            DirFileStatisticsRecord record = new DirFileStatisticsRecord();
            record.setDir(Boolean.TRUE);
            record.setDirFileName(childDir.getPath());

            result.add(record);
        }

        for (File childFile : dir.getChildFiles()) {
            DirFileStatisticsRecord record = new DirFileStatisticsRecord();
            record.setDir(Boolean.FALSE);
            record.setDirFileName(childFile.getName());
            record.setDirFileSize(childFile.getSize());

            result.add(record);
        }

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Dir save(String path) throws DirNotFoundException, IOException {
        Path pathObject = Paths.get(path);
        if (Files.notExists(pathObject)) {
            throw new DirNotFoundException();
        }

        LocalDateTime localDate = LocalDateTime.now();
        Dir dir = new Dir();
        dir.setCreated(localDate);
        dir.setPath(path);

        Map<Boolean, List<Path>> map;
        try (Stream<Path> stream = Files.walk(pathObject, 1)) {
            map = stream.filter(e -> !e.toString().equals(path)).collect(Collectors.partitioningBy(e -> Files.isDirectory(e)));
        }

        List<Path> dirPaths = map.get(Boolean.TRUE);
        if (dirPaths != null) {
            for (Path dirPath : dirPaths) {
                Dir childDir = new Dir();
                childDir.setCreated(localDate);
                childDir.setPath(dirPath.toString());
                dir.getChildDirs().add(childDir);
            }
        }
        List<Path> filePaths = map.get(Boolean.FALSE);
        if (filePaths != null) {
            for (Path filePath : filePaths) {
                File childFile = new File();
                childFile.setCreated(localDate);
                childFile.setName(filePath.toString());
                childFile.setSize(filePath.toFile().length());
                dir.getChildFiles().add(childFile);
            }
        }

        return dirRepository.save(dir);
    }

    @Override
    @Transactional(readOnly = true)
    public DirStatisticsRecord addToList(String path) throws DirNotFoundException, IOException {
        DirStatisticsRecord result = new DirStatisticsRecord();
        Dir savedDir = dirService.save(path);
        result.setId(savedDir.getId());
        result.setPath(savedDir.getPath());
        result.setCreated(savedDir.getCreated());

        result.setDirCount(savedDir.getChildDirs().size());
        result.setFileCount(savedDir.getChildFiles().size());

        long totalFileSize = 0;

        for (File file : savedDir.getChildFiles()) totalFileSize += file.getSize();

        result.setTotalFileSize(totalFileSize);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DirStatisticsRecord> getDirs() {
        List<DirStatisticsRecord> result = new ArrayList<>();

        for (Dir parentDir : dirRepository.findAllParentDirs()) {
            DirStatisticsRecord dirStatisticsRecord = new DirStatisticsRecord();
            dirStatisticsRecord.setId(parentDir.getId());
            dirStatisticsRecord.setPath(parentDir.getPath());
            dirStatisticsRecord.setCreated(parentDir.getCreated());
            dirStatisticsRecord.setDirCount(parentDir.getChildDirs().size());
            dirStatisticsRecord.setFileCount(parentDir.getChildFiles().size());

            long totalFileSize = 0;

            for (File file : parentDir.getChildFiles()) totalFileSize += file.getSize();

            dirStatisticsRecord.setTotalFileSize(totalFileSize);

            result.add(dirStatisticsRecord);
        }

        return result;
    }

    @Override
    public List<DirFileStatisticsRecord> sortByDirFileName(List<DirFileStatisticsRecord> records) {
        return records.stream()
                .sorted((e1, e2) -> e1.compareTo(e2))
                .collect(Collectors.toList());
    }
}
