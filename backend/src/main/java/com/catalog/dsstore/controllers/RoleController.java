package com.catalog.dsstore.controllers;

import com.catalog.dsstore.dto.RoleDTO;
import com.catalog.dsstore.entities.Role;
import com.catalog.dsstore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO obj){
        RoleDTO dto = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<RoleDTO>> findAll(){
        List<RoleDTO> dtoList = service.findAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<RoleDTO>> findById(@PathVariable Long id){
        Optional<RoleDTO> dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "5") Integer size){
        PageRequest request = PageRequest.of(page,size);
        Page<RoleDTO> dtoPage = service.findPaged(request);
        return ResponseEntity.ok().body(dtoPage);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @RequestBody RoleDTO obj){
        RoleDTO dto = service.update(id, obj);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
