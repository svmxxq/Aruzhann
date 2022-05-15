package kz.narxoz.narxozapp.service;

import kz.narxoz.narxozapp.model.Product;
import kz.narxoz.narxozapp.repository.ProductRepository;
import kz.narxoz.narxozapp.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RolesRepository roleRepository;


    public void save(Product product) {
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setImage(product.getImage());
        product.setCategory(product.getCategory());

        productRepository.save(product);
    }
}