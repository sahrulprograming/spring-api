package com.example.officebookingsystem.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "complexes",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "complexName")
        }
)
public class Complex {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "city_id")
        private City city;

        @ManyToOne
        @JoinColumn(name = "district_id")
        private District district;

        @ManyToOne
        @JoinColumn(name = "province_id")
        private Province province;

        @NotBlank
        private String complexName;

        @NotBlank
        private String address;
}
