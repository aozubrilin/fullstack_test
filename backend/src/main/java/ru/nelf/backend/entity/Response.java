package ru.nelf.backend.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Response {
    private int status;
    private String id;
    private String filename;
    private String category;
    private String url;

    public Response() {
    }

    public Response(int status, String filename) {
        this.status = status;
        this.filename = filename;
    }

    public Response(int status, String id, String filename, String category, String url) {
        this.status = status;
        this.id = id;
        this.filename = filename;
        this.category = category;
        this.url = url;
    }
}
