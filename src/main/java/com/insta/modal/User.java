package com.insta.modal;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;
    private String gender;


    private List<Integer> followers = new ArrayList<>();


    private List<Integer> following = new ArrayList<>();

    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();


}
