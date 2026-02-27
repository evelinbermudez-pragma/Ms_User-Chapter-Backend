package com.chapter.user.domain.model;

public class Role {
    Integer roleId;
    String name;

    public Role(){

    }
    public Role(Integer roleId, String name){
        this.roleId = roleId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setId(Integer roleId) {
        this.roleId = roleId;
    }
}
