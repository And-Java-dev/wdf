package com.example.common.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.mail.imap.protocol.ID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL ,mappedBy = "categories")
    private List<Material> materials;


}
