package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mouse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    @JoinColumn(name = "cat")
    private List<Cat> cats;

    public Mouse(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
