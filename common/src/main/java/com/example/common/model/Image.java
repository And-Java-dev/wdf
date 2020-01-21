package com.example.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;


    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL ,mappedBy = "images")
    private List<Product> products;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL ,mappedBy = "images")
    private List<Material> materials;
}
