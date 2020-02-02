package com.example.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materials")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column(name = "invoice_price")
    private double invoicePrice;

    @Column
    private double price;

    @OneToOne
    private Size size;

    @Column
    private double rating;

    @Column
    private int view;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private MaterialCategory materialCategory;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "material_category",
            joinColumns = @JoinColumn(name = "material_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "material_image",
            joinColumns = @JoinColumn(name = "material_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> images;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL ,mappedBy = "materials")
    private List<Product> products;

}
