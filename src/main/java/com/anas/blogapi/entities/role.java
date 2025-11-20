package com.anas.blogapi.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,name = "role_name")
    private String name;
}
