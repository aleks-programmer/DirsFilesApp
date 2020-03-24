package com.application.DirsFilesApp.service;

import com.application.DirsFilesApp.record.DirFileStatisticsRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DirServiceImplTest {

    private DirService dirService;

    @BeforeEach
    void setUp() {
        dirService = new DirServiceImpl();
    }

    @Test
    void sortByDirFileName() {
        List<DirFileStatisticsRecord> unsorted = new ArrayList<>();
        DirFileStatisticsRecord record1 = new DirFileStatisticsRecord();
        record1.setDirFileName("12375083275821");
        unsorted.add(record1);
        DirFileStatisticsRecord record2 = new DirFileStatisticsRecord();
        record2.setDirFileName("9879879.txt");
        unsorted.add(record2);
        DirFileStatisticsRecord record3 = new DirFileStatisticsRecord();
        record3.setDirFileName("a2hjk");
        unsorted.add(record3);
        DirFileStatisticsRecord record4 = new DirFileStatisticsRecord();
        record4.setDirFileName("sajfa28357kjhk");
        unsorted.add(record4);
        DirFileStatisticsRecord record5 = new DirFileStatisticsRecord();
        record5.setDirFileName("72508sjdgk3460");
        unsorted.add(record5);
        DirFileStatisticsRecord record6 = new DirFileStatisticsRecord();
        record6.setDirFileName("a11hjk");
        unsorted.add(record6);
        DirFileStatisticsRecord record7 = new DirFileStatisticsRecord();
        record7.setDirFileName("fsjlbls0970987097jbjkb8909");
        unsorted.add(record7);
        List<DirFileStatisticsRecord> sorted = dirService.sortByDirFileName(unsorted);
        assertEquals(unsorted.size(), sorted.size());

        DirFileStatisticsRecord prev = null;
        for (DirFileStatisticsRecord record : sorted) {
            if (prev != null) {
                assertTrue(record.compareTo(prev) > 0);
            }
            prev = record;
            System.out.println(record.getDirFileName());
        }
    }
}