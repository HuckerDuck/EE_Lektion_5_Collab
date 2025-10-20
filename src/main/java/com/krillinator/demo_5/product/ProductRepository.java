package com.krillinator.demo_5.product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface ProductRepository extends R2dbcRepository<Product, Long> {

    Long id(Long id);
}
