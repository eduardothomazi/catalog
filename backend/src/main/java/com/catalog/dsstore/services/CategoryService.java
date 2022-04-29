package com.catalog.dsstore.services;

import com.catalog.dsstore.dto.CategoryDTO;
import com.catalog.dsstore.entities.Category;
import com.catalog.dsstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll(){
        List<Category> obj = repository.findAll();
        List<CategoryDTO> dto = obj.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
        return dto;
    }

    public Optional<CategoryDTO> findById(Long id){
        Optional<Category> optional = repository.findById(id);
        Optional<CategoryDTO> dto = Optional.of(new CategoryDTO(optional.get()));
        return dto;
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }


}
