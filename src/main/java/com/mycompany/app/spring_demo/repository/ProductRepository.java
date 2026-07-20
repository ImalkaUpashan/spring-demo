package com.mycompany.app.spring_demo.repository;

import com.mycompany.app.spring_demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(idCounter.incrementAndGet());
        }
        store.put(product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void deleteById(Long id) {
        store.remove(id);
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}