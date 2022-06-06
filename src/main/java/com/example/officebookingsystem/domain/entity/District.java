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
    @GeneratedValue
    private Long id;

    private String name;
}
