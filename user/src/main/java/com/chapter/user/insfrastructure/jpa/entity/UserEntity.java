package com.chapter.user.insfrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "User")
public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private String lastName;
        @Column(unique = true)
        private Integer document;
        private Date dateOfBirth;
        private String phone;
        @Column(unique = true)
        private String email;
        @Column(name = "password")
        private String password;
        private Integer role;
        private String token;

}
