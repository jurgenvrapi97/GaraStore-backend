package com.jurgenvrapi.garastore.repositories;

import com.jurgenvrapi.garastore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}