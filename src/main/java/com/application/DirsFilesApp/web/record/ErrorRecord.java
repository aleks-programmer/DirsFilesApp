package com.application.DirsFilesApp.web.record;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ErrorRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    public ErrorRecord() {}
    public ErrorRecord(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonProperty
    private Integer code;

    @JsonProperty
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
