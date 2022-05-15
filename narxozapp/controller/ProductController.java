package kz.narxoz.narxozapp.controller;



import kz.narxoz.narxozapp.model.Book;
import kz.narxoz.narxozapp.model.Product;
import kz.narxoz.narxozapp.model.User;
import kz.narxoz.narxozapp.repository.ProductRepository;
import kz.narxoz.narxozapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {




    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ProductService productService;



//    @GetMapping("productler")
//    public List<Product> getAll(){
//        return productRepository.findAll();
//    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("productForm", new Product());

        return "register";

    }


    @PostMapping("/register")
    public String register(@ModelAttribute("productForm") Product productForm) {

        productService.save(productForm);

        return "redirect:/";

    }





}
