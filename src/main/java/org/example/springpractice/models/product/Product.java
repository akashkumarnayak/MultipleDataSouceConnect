package org.example.springpractice.models.product;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.example.springpractice.models.BaseModel;

import java.util.Date;

@Entity
@Getter
@Setter
public class Product extends BaseModel {

    private String name;
    private String description;
    private float price;

    public Product() {
        createdAt = new Date();
        lastUpdatedAt = new Date();
    }
}
