package ru.nelf.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nelf.backend.entity.Category;
import ru.nelf.backend.entity.Request;
import ru.nelf.backend.entity.Response;
import ru.nelf.backend.entity.ResponseCategoryFiles;
import ru.nelf.backend.service.StorageService;

@RestController
@CrossOrigin
@Slf4j
public class Controller {

    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/")
    public ResponseCategoryFiles getCategoryFiles(@RequestParam String category) {
        log.info("getCategoryFiles(" + category + ")");
        return category.equalsIgnoreCase(Category.ALL)
                ? storageService.getAll() : storageService.getByCategory(category.toLowerCase());
    }

    @PostMapping("/mongo/refresh")
    public ResponseCategoryFiles refreshMongo() {
        log.info("refreshMongo()");
        storageService.refreshMongo();
        return storageService.getAll();
    }

    @GetMapping(value = "/{filename}")
    public Response getImage(@PathVariable String filename) {
        log.info("getImage(" + filename + ")");
        return storageService.getImage(filename + ".jpg");
    }

    @PostMapping(value = "/")
    public Response putImage(@RequestBody Request request) {
        log.info("putImage(" + request.toString() + ")");
        return storageService.putImage(request);
    }

    @DeleteMapping(value = "/{filename}")
    public Response deleteImage(@PathVariable String filename) {
        log.info("deleteImage(" + filename + ")");
        return storageService.deleteImage(filename + ".jpg");
    }
}
