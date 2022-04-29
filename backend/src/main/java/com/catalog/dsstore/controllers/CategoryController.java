package com.catalog.dsstore.controllers;

import com.catalog.dsstore.dto.CategoryDTO;
import com.catalog.dsstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<CategoryDTO>> findById(@PathVariable Long id){
        Optional<CategoryDTO> dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
