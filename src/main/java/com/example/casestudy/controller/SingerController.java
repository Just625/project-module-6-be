package com.example.casestudy.controller;

import com.example.casestudy.model.Singer;
import com.example.casestudy.service.singer.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SingerController {
    @Autowired
    private ISingerService singerService;

    @GetMapping("singers")
    public ResponseEntity<Iterable<Singer>> getAllGenre (){
        return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
    }
}
