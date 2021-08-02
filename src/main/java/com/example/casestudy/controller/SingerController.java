package com.example.casestudy.controller;

import com.example.casestudy.model.Singer;
import com.example.casestudy.service.singer.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/singers")
@CrossOrigin("*")
public class SingerController {
    @Autowired
    private ISingerService singerService;


   @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
   }

   @GetMapping("/{id}")
    public ResponseEntity<Singer> getSinger(@PathVariable Long id){
       Optional<Singer> singerOptional = singerService.findById(id);
       if (singerOptional.isPresent()){
           return new ResponseEntity<>(singerOptional.get(),HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
    @PostMapping
    public ResponseEntity<Singer> save(@RequestBody Singer singer){
        return new ResponseEntity<>(singerService.save(singer), HttpStatus.CREATED);
    }
   @PutMapping("/{id}")
    public ResponseEntity<Singer> update(@PathVariable Long id,@RequestBody Singer singer){
       Optional<Singer> singerOptional = singerService.findById(id);
       if (singerOptional.isPresent()){
           return new ResponseEntity<>(singerService.save(singer),HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
   @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
       Optional<Singer> singerOptional = singerService.findById(id);
       if (singerOptional.isPresent()){
           singerService.deleteById(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
}
