package com.daovantam.quanlydaotao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "role")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();
}
