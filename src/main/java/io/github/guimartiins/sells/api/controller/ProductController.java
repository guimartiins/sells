package io.github.guimartiins.sells.api.controller;

import io.github.guimartiins.sells.domain.entity.Product;
import io.github.guimartiins.sells.domain.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        productRepository
                .findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return Void.TYPE;
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody Product product) {
        productRepository.findById(id)
                .map(productFound -> {
                    product.setId(productFound.getId());
                    productRepository.save(product);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }


    @GetMapping
    public List<Product> getAll(Product filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Product> example = Example.of(filter, matcher);
        return productRepository.findAll(example);
    }
}
