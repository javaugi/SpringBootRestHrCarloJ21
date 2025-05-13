package com.jinvindia.inventory.repositories;

import com.jinvindia.inventory.entities.RecalledProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecalledProductRepository extends JpaRepository<RecalledProduct, Integer> {}
