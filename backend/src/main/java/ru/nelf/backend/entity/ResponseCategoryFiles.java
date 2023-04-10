package ru.nelf.backend.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ResponseCategoryFiles {
    private int code;
    private List<File> fileList;

    public ResponseCategoryFiles(int code, List<File> fileList) {
        this.code = code;
        this.fileList = fileList;
    }
}
