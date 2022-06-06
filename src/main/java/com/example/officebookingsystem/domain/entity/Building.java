package com.example.officebookingsystem.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "buildings",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
        }
)
public class Building {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "complex_id")
    private Complex complex;

    @NotBlank
    private String name;
    
    @NotBlank
    private String address;

    @NotBlank
    private String description;

}
