package com.example.demo1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.MainModel;
import com.example.demo1.repository.MainRepository;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    MainRepository mainRepository;

    @GetMapping("/show_all")
    public List<MainModel> getAllRows(){
        return (List<MainModel>) mainRepository.findAll();

    }
    
    @PostMapping("/insert")
    public ResponseEntity<MainModel> insertValues(@RequestBody MainModel model){
        MainModel _model = mainRepository.save(new MainModel( model.getName(), model.getAge()));
        return new ResponseEntity<> (_model, HttpStatus.OK);
        
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<MainModel> deleteAllRows(){
        mainRepository.deleteAll();
        return new ResponseEntity<MainModel>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<MainModel> updateRow(@PathVariable("id") Long id, @RequestBody MainModel model){
        Optional<MainModel> _model = mainRepository.findById(id);
        if(_model.isPresent()){
            MainModel __model = _model.get();
            __model.setName(model.getName());
            __model.setAge(model.getAge());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/show/{id}")
    public Optional<MainModel> findbyid(@PathVariable Long id){
        return (Optional<MainModel>) mainRepository.findById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
        Optional<MainModel> ____model = mainRepository.findById(id);
        if (____model != null) {
            mainRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
