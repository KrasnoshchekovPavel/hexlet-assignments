package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found", id)));
        return productMapper.map(product);
    }

    @GetMapping
    public List<ProductDTO> getAll(){
        return productRepository.findAll().stream()
                .map(productMapper::map)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO dto){

        categoryRepository.findById(dto.getCategoryId()).orElseThrow(() ->
                new ValidationException(String.format("Category with id %s not found", dto.getCategoryId())));

        Product product = productMapper.map(dto);
        return productMapper.map(productRepository.save(product));
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO dto){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found", id)));
        if (dto.getCategoryId().isPresent()){
            categoryRepository.findById(dto.getCategoryId().get()).orElseThrow(() ->
                    new ValidationException(String.format("Category with id %s not found", dto.getCategoryId())));

        }

        productMapper.update(dto, product);
        productRepository.save(product);
        return productMapper.map(product);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found", id)));
        productRepository.deleteById(id);

    }
    // END
}
