//package kz.narxoz.narxozapp.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Data
//@Table(name="basket")
//public class Basket {
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    private long id;
//
//    private String user;
//
//    private String details;
//
//
//    @OneToOne
//    @JoinColumn(name="user_id")
//    private User owner;
//
//    @ManyToMany
//    @JoinTable(name="basket_products" , joinColumns=@JoinColumn(name="basket_id"),inverseJoinColumns=@JoinColumn(name="products_id"))
//    private List<Product> products;
//}

