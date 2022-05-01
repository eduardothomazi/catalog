package com.catalog.dsstore.services;

import com.catalog.dsstore.dto.CategoryDTO;
import com.catalog.dsstore.entities.Category;
import com.catalog.dsstore.repositories.CategoryRepository;
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
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> obj = repository.findAll();
        List<CategoryDTO> dto = obj.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
        return dto;
    }

    @Transactional(readOnly = true)
    public Optional<CategoryDTO> findById(Long id) {
        Optional<Category> optional = repository.findById(id);
        Optional<CategoryDTO> dto = Optional.of(new CategoryDTO(optional.get()));
        return dto;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CategoryDTO create(CategoryDTO dto) throws Exception {
        if (dto.getId() == null) {
            repository.save(new Category(dto));
            return dto;
        } else throw new Exception("This id already exists!");
    }

    public CategoryDTO update(Long id, CategoryDTO obj) {
        try {
            Optional<Category> category = repository.findById(id);
            obj.setId(category.get().getId());
            repository.save(category.get());
            return obj;
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findPaged(PageRequest request){
        Page<Category> categoryPage = repository.findAll(request);
        Page<CategoryDTO> dtoPage = categoryPage.map(x -> new CategoryDTO(x));
        return dtoPage;
    }


}
