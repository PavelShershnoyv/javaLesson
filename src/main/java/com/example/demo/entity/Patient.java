package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "patients", schema = "clinic")
public class Patient {

    @Id
    private int id;

    private String name;

    private String surname;

    private Integer policy;

    private String diagnosis;

    private String placeResidence;
}
