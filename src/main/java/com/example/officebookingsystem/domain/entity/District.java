package com.example.officebookingsystem.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "disctricts",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
        }
)
public class District {
    @Id
    @SequenceGenerator(
            name = "district_sequence",
            sequenceName = "district_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "district_sequence"
    )

    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String name;
}
