package org.example.springpractice.models.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.example.springpractice.models.BaseModel;

import java.util.Date;

@Entity
@Getter
@Setter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private Status status;
    private Date createdAt;
    private Date lastUpdatedAt;

    public User() {
        createdAt = new Date();
        lastUpdatedAt = new Date();
        status = Status.ACTIVE;
    }
}
