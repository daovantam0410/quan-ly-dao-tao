package com.daovantam.quanlydaotao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, columnDefinition = "varchar(10)")
    private String code;
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "branch")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "branch")
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "branch")
    private Set<Teacher> teachers = new HashSet<>();

}
