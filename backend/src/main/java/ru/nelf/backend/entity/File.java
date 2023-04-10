package ru.nelf.backend.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "file")
@Data
@ToString
public class File {
    @MongoId
    private String id;
    private String filename;
    private String category;
    private String url;

    public File(String filename, String category, String url) {
        this.filename = filename;
        this.category = category;
        this.url = url;
    }
}
