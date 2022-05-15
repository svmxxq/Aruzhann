package kz.narxoz.narxozapp.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Roles> roles;

//    private Basket basket;


}
