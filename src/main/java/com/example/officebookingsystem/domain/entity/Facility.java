package com.example.officebookingsystem. domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facilities")
public class Facility {
    @Id
    @SequenceGenerator(name = "facility_sequence", sequenceName = "facility_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "facility_category_id")
    private Facility_Category facility_category;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    private String name;

    private Float distance;

    private Integer duration;


}
