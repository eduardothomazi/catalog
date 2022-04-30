package com.catalog.dsstore.services;

import com.catalog.dsstore.dto.ProductDTO;
import com.catalog.dsstore.entities.Product;
import com.catalog.dsstore.repositories.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO create(ProductDTO dto) {
        dto.setId(null);
        Product product = new Product(dto);
        repository.save(product);
        dto.setId(product.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> product = repository.findById(id);
        ProductDTO dto = new ProductDTO(product.get());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> productList = repository.findAll();
        List<ProductDTO> dtoList = productList.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
        return dtoList;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findPaged(PageRequest request){
        Page<Product> products = repository.findAll(request);
        Page<ProductDTO> dtoPage = products.map(x -> new ProductDTO(x));
        return dtoPage;
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            dto.setId(id);
            repository.save(new Product(dto));
            return dto;
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
