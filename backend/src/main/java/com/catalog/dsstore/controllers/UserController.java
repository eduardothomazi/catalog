package com.catalog.dsstore.controllers;

import com.catalog.dsstore.dto.UserDTO;
import com.catalog.dsstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO obj) {
        UserDTO dto = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> dtoList = service.findAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<UserDTO>> findById(@PathVariable Long id) {
        Optional<UserDTO> dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "5") Integer size) {
        PageRequest request = PageRequest.of(page, size);
        Page<UserDTO> dtoPage = service.findPaged(request);
        return ResponseEntity.ok().body(dtoPage);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO obj) {
        UserDTO dto = service.update(id, obj);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
