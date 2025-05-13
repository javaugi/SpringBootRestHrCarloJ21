package com.jinvindia.inventory.repositories;

import com.jinvindia.inventory.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository<Product, Integer> {}
