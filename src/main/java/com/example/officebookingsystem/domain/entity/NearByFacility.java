package com.example.officebookingsystem.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facilities")
public class NearByFacility {
    @Id
    @SequenceGenerator(name = "facility_sequence", sequenceName = "facility_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facility_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @NotBlank
    private String name;

    private String description;
}
