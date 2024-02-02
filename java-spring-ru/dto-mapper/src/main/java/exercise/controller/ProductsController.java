package exercise.controller;

import exercise.model.Product;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id){
        return productMapper.map(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found", id))));
    }

    @GetMapping
    public List<ProductDTO> getAll(){
        return productRepository.findAll().stream().map(productMapper::map).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO dto){
        return productMapper.map(productRepository.save(productMapper.map(dto)));
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductUpdateDTO dto){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found", id)));
        productMapper.update(dto, product);
        return productMapper.map(productRepository.save(product));
    }

    // END
}
