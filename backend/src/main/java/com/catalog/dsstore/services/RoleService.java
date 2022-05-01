package com.catalog.dsstore.services;

import com.catalog.dsstore.dto.RoleDTO;
import com.catalog.dsstore.entities.Role;
import com.catalog.dsstore.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public RoleDTO create(RoleDTO obj) {
        return obj;
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        List<Role> roleList = repository.findAll();
        List<RoleDTO> dtoList = roleList.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
        return dtoList;
    }

    @Transactional(readOnly = true)
    public Optional<RoleDTO> findById(Long id) {
        Optional<Role> role = repository.findById(id);
        Optional<RoleDTO> dto = role.map(x -> new RoleDTO(x));
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<RoleDTO> findPaged(PageRequest request) {
        Page<Role> rolePage = repository.findAll(request);
        Page<RoleDTO> dtoPage = rolePage.map(x -> new RoleDTO(x));
        return dtoPage;
    }

    public RoleDTO update(Long id, RoleDTO obj) {
        try {
            Optional<Role> role = repository.findById(id);
            obj.setId(role.get().getId());
            repository.save(role.get());
            return obj;
        }catch (EntityNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
