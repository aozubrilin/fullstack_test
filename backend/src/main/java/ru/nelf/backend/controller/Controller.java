package ru.nelf.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nelf.backend.entity.Category;
import ru.nelf.backend.entity.Request;
import ru.nelf.backend.entity.Response;
import ru.nelf.backend.entity.ResponseCategoryFiles;
import ru.nelf.backend.service.MainService;

@RestController
@CrossOrigin
@Slf4j
public class Controller {

    @Autowired
    private MainService mainService;

    @GetMapping(value = "/")
    public ResponseCategoryFiles getCategoryFiles(@RequestParam String category) {
        log.info("getCategoryFiles(" + category + ")");
        return category.equalsIgnoreCase(Category.ALL)
                ? mainService.getAll() : mainService.getByCategory(category.toLowerCase());
    }

    @PostMapping("/mongo/refresh")
    public ResponseCategoryFiles refreshMongo() {
        log.info("refreshMongo()");
        mainService.refreshMongo();
        return mainService.getAll();
    }

    @GetMapping(value = "/{filename}")
    public Response getImage(@PathVariable String filename) {
        log.info("getImage(" + filename + ")");
        return mainService.getImage(filename + ".jpg");
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response fileUpload(@RequestParam("filename") String filename,
                               @RequestParam("category") String category,
                               @RequestParam(value = "url",required = false) String url,
                               @RequestParam("file") MultipartFile multipartFile) {
        log.info("fileUpload(" + filename + "," + category + "," + url + "," + multipartFile + ")");
        return mainService.putImage(new Request(filename, category, url), multipartFile);
    }

    @DeleteMapping(value = "/{filename}")
    public Response deleteImage(@PathVariable String filename) {
        log.info("deleteImage(" + filename + ")");
        return mainService.deleteImage(filename + ".jpg");
    }
}
