package ru.nelf.backend.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Request {
    private String filename;
    private String category;
    private String url;

    public Request(String filename, String category, String url) {
        this.filename = filename;
        this.category = category;
        this.url = url;
    }

}
