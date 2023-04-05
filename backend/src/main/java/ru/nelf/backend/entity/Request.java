package ru.nelf.backend.entity;

import lombok.Data;

@Data
public class Request {
    String filename;
    String url;

    public Request(String filename, String url) {
        this.filename = filename;
        this.url = url;
    }
}
