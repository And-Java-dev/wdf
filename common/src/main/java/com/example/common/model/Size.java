package com.example.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sizes")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double height;

    @Column
    private double width;

    @Column
    private double length;

    @Column
    private double weight;

    @Column(name = "square_meter")
    private double squareMeter;

    @Column
    private double thickness;
}
