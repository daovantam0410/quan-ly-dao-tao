package com.daovantam.quanlydaotao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, columnDefinition = "varchar(5)")
    private String name;

    @OneToMany(mappedBy = "gender")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "gender")
    private List<Teacher> teachers = new ArrayList<>();
}
