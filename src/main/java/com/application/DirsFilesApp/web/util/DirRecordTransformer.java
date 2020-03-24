package com.application.DirsFilesApp.web.util;

import com.application.DirsFilesApp.domain.Dir;
import com.application.DirsFilesApp.domain.File;
import com.application.DirsFilesApp.web.record.DirRecord;
import com.application.DirsFilesApp.web.record.FileRecord;

public class DirRecordTransformer {
    public static DirRecord transformEntityToRecord(Dir dir) {
        DirRecord dirRecord = new DirRecord();
        dirRecord.setId(dir.getId());
        dirRecord.setPath(dir.getPath());
        dirRecord.setCreated(dir.getCreated());

        for (Dir entity : dir.getChildDirs()) {
            DirRecord childDir = new DirRecord();
            childDir.setId(entity.getId());
            childDir.setPath(entity.getPath());
            childDir.setCreated(entity.getCreated());
            dirRecord.getChildDirs().add(childDir);
        }
        for (File entity : dir.getChildFiles()) {
            dirRecord.getChildFiles()
                    .add(FileRecordTransformer
                            .transformEntityToRecord(entity));
        }

        return dirRecord;
    }

    public static Dir transformRecordToEntity(DirRecord dirRecord) {
        Dir dir = new Dir();
        dir.setId(dirRecord.getId());
        dir.setPath(dirRecord.getPath());
        dir.setCreated(dirRecord.getCreated());

        for (DirRecord record : dirRecord.getChildDirs()) {
            Dir childDir = new Dir();
            childDir.setId(record.getId());
            childDir.setPath(record.getPath());
            childDir.setCreated(record.getCreated());
            dir.getChildDirs().add(childDir);
        }
        for (FileRecord record : dirRecord.getChildFiles()) {
            dir.getChildFiles()
                    .add(FileRecordTransformer
                            .transformRecordToEntity(record));
        }

        return dir;
    }

}
