package com.simpleproject.hospital_management.model;

import lombok.Data;

@Data
//@Data adds all getter setter internally
public class Doctor {

    private String name;
    private int id;
    private int age;
    private String specialization;
    private String email;
}
