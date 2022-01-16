package com.excel.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.excel.project.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
