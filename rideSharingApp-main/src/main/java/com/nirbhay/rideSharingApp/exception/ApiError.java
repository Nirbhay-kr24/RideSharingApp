package com.nirbhay.rideSharingApp.exception;

import java.time.LocalDateTime;

public class ApiError {

    final private LocalDateTime timestamp;
    final private int status;
    final private String error;
    final private String path;

    public ApiError(int status, String error, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getPath() { return path; }
}
