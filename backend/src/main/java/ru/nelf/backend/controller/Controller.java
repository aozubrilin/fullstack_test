package ru.nelf.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nelf.backend.entity.Request;
import ru.nelf.backend.entity.Response;
import ru.nelf.backend.entity.ResponseCategoryFiles;
import ru.nelf.backend.service.StorageService;

@RestController
public class Controller {

    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/all")
    public ResponseCategoryFiles getAllImages() {
        return storageService.getAll();
    }

    @GetMapping(value = "/{filename}")
    public Response getImage(@PathVariable String filename) {
        return storageService.getImage(filename + ".jpg");
    }

    @GetMapping(value = "/")
    public ResponseCategoryFiles getCategoryFiles(@RequestParam String category){
        return storageService.findByCategory(category.toLowerCase());
    }

    @PostMapping(value = "/")
    public Response putImage(@RequestBody Request request) {
        return storageService.putImage(request);
    }

    @DeleteMapping(value = "/{filename}")
    public Response deleteImage(@PathVariable String filename){
        return storageService.deleteImage(filename + ".jpg");
    }
}
