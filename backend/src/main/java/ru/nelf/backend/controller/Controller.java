package ru.nelf.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nelf.backend.entity.Request;
import ru.nelf.backend.entity.Response;
import ru.nelf.backend.service.StorageService;

@RestController
public class Controller {

    @Autowired
    StorageService storageService;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getImage(@PathVariable String id) {
        return new Response(storageService.getImage(id+".jpg"));
    }

    @PostMapping(value = "/")
    public void putImage(@RequestBody Request request){
        storageService.putImage(request.getUrl());
    }
}
