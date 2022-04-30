package com.catalog.dsstore.controllers;

import com.catalog.dsstore.dto.ProductDTO;
import com.catalog.dsstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> dtoList = service.findAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "5")Integer size){
        PageRequest request = PageRequest.of(page,size);
        Page<ProductDTO> dtoPage = service.findPaged(request);
        return ResponseEntity.ok().body(dtoPage);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
