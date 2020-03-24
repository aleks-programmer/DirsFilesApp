package com.application.DirsFilesApp.service;

import com.application.DirsFilesApp.domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }
}
