package com.chapter.user.insfrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
        private String phone;
        private String email;
        private String password;

        @ManyToOne
        @JoinColumn(name = "role_id")
        private RoleEntity role;
}
