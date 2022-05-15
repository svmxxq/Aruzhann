package kz.narxoz.narxozapp.repository;


import kz.narxoz.narxozapp.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {



    Product findByName(String name);

}
