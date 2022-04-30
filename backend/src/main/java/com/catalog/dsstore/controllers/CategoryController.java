package com.catalog.dsstore.controllers;

import com.catalog.dsstore.dto.CategoryDTO;
import com.catalog.dsstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) throws Exception {
        CategoryDTO categoryDTO = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO obj) throws Exception {
        CategoryDTO categoryDTO = service.update(id, obj);
        return ResponseEntity.ok().body(categoryDTO);
    }
}
