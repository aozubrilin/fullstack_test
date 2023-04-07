package ru.nelf.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nelf.backend.entity.File;
import ru.nelf.backend.entity.Request;
import ru.nelf.backend.entity.Response;
import ru.nelf.backend.service.StorageService;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/")
    public List<File> getAllImages() {
        return storageService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Response getImage(@PathVariable String id) {
        return storageService.getImage(id + ".jpg");
    }

    @PostMapping(value = "/")
    public Response putImage(@RequestBody Request request) {
        return storageService.putImage(request);
    }
}
