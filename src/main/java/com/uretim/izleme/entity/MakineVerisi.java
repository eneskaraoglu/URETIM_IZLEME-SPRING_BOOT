package com.uretim.izleme.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Document(collection = "makine_database")
public class MakineVerisi {

    @Id
    private String id;
    private Date time;
    private String makine;
    private String status;
    private int yuk;

    // Getters ve Setters
}
