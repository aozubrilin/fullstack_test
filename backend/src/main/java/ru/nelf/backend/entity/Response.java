package ru.nelf.backend.entity;

import lombok.Data;

@Data
public class Response {
    private String url;

    public Response(String url) {
        this.url = url;
    }
}
