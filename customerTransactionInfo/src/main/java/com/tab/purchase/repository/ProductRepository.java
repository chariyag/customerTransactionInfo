package com.tab.purchase.repository;

import com.tab.purchase.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

//   List<Product> findAllBy
}
