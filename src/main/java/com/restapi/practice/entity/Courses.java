package com.restapi.practice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Courses {

    @Id
    private long id;
    private String title;
    private String description;
}
