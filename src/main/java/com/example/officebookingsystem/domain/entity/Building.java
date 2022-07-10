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
@Table(name = "buildings", uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
})
public class Building {
        @Id
        @SequenceGenerator(name = "building_sequence", sequenceName = "building_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_sequence")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "complex_id")
        private Complex complex;

        @NotBlank(message = "Building name is required")
        private String name;

        @NotBlank(message = "Building address is required")
        private String address;

        @NotBlank(message = "Building description is required")
        private String description;

        @Lob
        private String building_image;

        private String image_type;



}
