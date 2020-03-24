package com.application.DirsFilesApp.web;

import com.application.DirsFilesApp.exception.DirNotFoundException;
import com.application.DirsFilesApp.service.DirService;
import com.application.DirsFilesApp.web.record.ErrorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class DirFileController {

    @Autowired
    private DirService dirService;

    @GetMapping(value = "/files", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity files(@RequestParam("dirId") String dirId) {
        try {
            return ResponseEntity
                    .ok(dirService.getFiles(Integer.valueOf(dirId)));
        } catch (RuntimeException e) {
            ErrorRecord errorRecord = new ErrorRecord(HttpStatus.INTERNAL_SERVER_ERROR.value()
                    ,"Непредвиденная ошибка");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRecord);
        } catch (DirNotFoundException e) {
            ErrorRecord errorRecord = new ErrorRecord(HttpStatus.INTERNAL_SERVER_ERROR.value()
                    ,"Директория не найдена");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRecord);
        }
    }

    @GetMapping(value = "/dirs", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity dirs() {
        try {
            return ResponseEntity
                    .ok(dirService.getDirs());
        } catch (RuntimeException e) {
            ErrorRecord errorRecord = new ErrorRecord(HttpStatus.INTERNAL_SERVER_ERROR.value()
                    , "Непредвиденная ошибка");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRecord);
        }
    }

    @PostMapping(value = "/addDirToList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseEntity addDirToList(@RequestBody String path) {
        try {
            return ResponseEntity
                    .ok(dirService.addToList(path));
        } catch (DirNotFoundException e) {
            ErrorRecord errorRecord = new ErrorRecord(HttpStatus.INTERNAL_SERVER_ERROR.value()
                    ,"Директория не найдена");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRecord);
        } catch (IOException e) {
            ErrorRecord errorRecord = new ErrorRecord(HttpStatus.INTERNAL_SERVER_ERROR.value()
                    ,"Непредвиденная ошибка. Попробуйте выполнить запрос чуть позже");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRecord);
        }
    }
}
