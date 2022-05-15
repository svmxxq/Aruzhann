package kz.narxoz.narxozapp.controller;

import kz.narxoz.narxozapp.model.Product;
import kz.narxoz.narxozapp.model.User;
import kz.narxoz.narxozapp.repository.ProductRepository;
import kz.narxoz.narxozapp.repository.UserRepository;
import kz.narxoz.narxozapp.service.ProductService;
import lombok.Getter;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

@Controller
public class WebController {

    @Autowired
    UserRepository repository;
    @Autowired
    ProductRepository repository2;
    private Locale FilenameUtils;

    @GetMapping("/")
    public String ProductList( Model model) {
        return "index";
    }
    @GetMapping("/products")
    public String showProductList(@RequestParam(name="name", required=false, defaultValue="") String name, Model model) {
        List<Product> products=repository2.findAll();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/productler")
    public String showProductlerList(@RequestParam(name="name", required=false, defaultValue="") String name, Model model) {
        List<Product> products=repository2.findAll();
        model.addAttribute("products", products);
        return "productler";
    }


    @GetMapping("/users")
    public String showUserList(@RequestParam(name="name", required=false, defaultValue="") String name, Model model) {
        List<User> users=repository.findAll();
        model.addAttribute("users", users);
        return "index";
    }



//    @GetMapping(value = "/adminpanel")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public String adminPage(Model model) {
//        model.addAttribute("Username",repository.getUsername());
//        return "index";
//    }
//
//    @GetMapping(value = "/userpanel")
//    @PreAuthorize("hasAnyRole('USER')")
//    public String userPage(Model model) {
//        model.addAttribute("Username",repository.getUsername());
//        return "userpanel";
//    }


//    @GetMapping(value = "/403")
//    public String accessDeniedPage(Model model) {
//        model.addAttribute("Username",repository.getUsername());
//        return "403";
//    }
//

    @PostMapping("/addproduct")
    public String createProduct(@ModelAttribute Product product){
        addProduct(product);
        return "add-product";
    }
    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user){
        addUser(user);
        return "add-user";
    }
    @GetMapping("/addnewproduct")
    public String addnewproduct(Model model) {

        model.addAttribute("productForm", new Product());

        return "add-product";

    }
    private void addUser(User newUser) {
        repository.save(newUser);
    }
    @Autowired
    private ProductService productService;

    @PostMapping("/addnewproduct")
    public String addnewproduct(@ModelAttribute("productForm") Product productForm) {

        productService.save(productForm);

        return "redirect:/";

    }



    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id, Product product) {
        updateProduct(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = repository2.getById(id);
        model.addAttribute("product", product);
        return "update-product";
    }

    private void deleteById(long id) {
        repository2.deleteById(id);
    }
    private void addProduct(Product newProduct) {
        repository2.save(newProduct);
    }

    private void updateProduct(Product updateProduct) {
        Product oldProduct = repository2.getById(updateProduct.getId());

        oldProduct.setName(updateProduct.getName());
        oldProduct.setPrice(updateProduct.getPrice());
        oldProduct.setImage(updateProduct.getImage());
        oldProduct.setCategory(updateProduct.getCategory());

        repository2.save(oldProduct);
    }


    
    
}
