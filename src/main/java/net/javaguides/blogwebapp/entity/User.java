package net.javaguides.blogwebapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
//@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    //many-to-many map between user and role
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL) //whenever we load user, it also loads role
    //create a table for many-to-many relationship. columns:user_id,role_id
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();
}
