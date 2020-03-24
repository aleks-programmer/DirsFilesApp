package com.application.DirsFilesApp.web.util;

import com.application.DirsFilesApp.domain.File;
import com.application.DirsFilesApp.web.record.FileRecord;

public class FileRecordTransformer {
    public static FileRecord transformEntityToRecord(File file) {
            FileRecord fileRecord = new FileRecord();
            fileRecord.setId(file.getId());
            fileRecord.setName(file.getName());
            fileRecord.setSize(file.getSize());
            fileRecord.setCreated(file.getCreated());

        return fileRecord;
    }
    public static File transformRecordToEntity(FileRecord fileRecord) {
            File file = new File();
            file.setId(fileRecord.getId());
            file.setName(fileRecord.getName());
            file.setSize(fileRecord.getSize());
            file.setCreated(fileRecord.getCreated());

        return file;
    }

}
