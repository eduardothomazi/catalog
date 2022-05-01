package com.catalog.dsstore.services;

import com.catalog.dsstore.dto.UserDTO;
import com.catalog.dsstore.entities.User;
import com.catalog.dsstore.repositories.UserRepository;
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
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO create(UserDTO dto) {
        dto.setId(null);
        User user = new User(dto);
        repository.save(user);
        dto.setId(user.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = repository.findById(id);
        Optional<UserDTO> dto = user.map(x -> new UserDTO(x));
        return dto;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();
        List<UserDTO> dtoList = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return dtoList;
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findPaged(PageRequest request) {
        Page<User> userPage = repository.findAll(request);
        Page<UserDTO> dtoPage = userPage.map(x -> new UserDTO(x));
        return dtoPage;
    }

    public UserDTO update(Long id, UserDTO obj) {
        try {
            Optional<User> user = repository.findById(id);
            obj.setId(user.get().getId());
            repository.save(user.get());
            return obj;
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
