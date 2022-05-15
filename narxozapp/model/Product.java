package kz.narxoz.narxozapp.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;

    private double price;

    private byte[] image;

    private String category;

    @ManyToMany
    private Set<Roles> roles;

    @ManyToOne
    private User owner;
}

