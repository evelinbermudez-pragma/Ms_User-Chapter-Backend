package com.chapter.user.domain.model;

import java.util.Date;

public class User {
    private Integer id;
    private String name;
    private String lastname;
    private Long document;
    private String phone;
    private String email;
    private Date birthDate;
    private String password;
    private String token;
    private Integer role;
    public User(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(Integer id, String name, String lastname, Long document, String phone, String email, Date birthDate, Integer role, String password, String token) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.document = document;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
        this.password = password;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
