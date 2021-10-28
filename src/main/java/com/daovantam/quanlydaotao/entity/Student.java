package com.daovantam.quanlydaotao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, columnDefinition = "varchar(10)")
    private String code;
    @Column(nullable = false)
    private String password;
    @Column
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column
    private String address;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private String time;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToMany(mappedBy = "student")
    private Set<Subject> subjects = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
