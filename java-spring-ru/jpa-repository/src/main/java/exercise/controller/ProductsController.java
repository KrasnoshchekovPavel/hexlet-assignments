package exercise.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> showAll(@RequestParam Optional<Integer> min, @RequestParam Optional<Integer> max) {
        if(min.isPresent() && max.isPresent()){
            return productRepository.findByPriceBetweenOrderByPriceAsc(min.get(), max.get());
        } else if (min.isPresent()) {
            return productRepository.findByPriceGreaterThanOrderByPriceAsc(min.get());
        } else if (max.isPresent()) {
            return productRepository.findByPriceLessThanOrderByPriceAsc(max.get());
        }

        return productRepository.findAll();
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
