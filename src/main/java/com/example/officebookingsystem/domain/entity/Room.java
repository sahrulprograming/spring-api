package com.example.officebookingsystem.domain.entity;

import com.example.officebookingsystem.domain.model.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "rooms",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
        }
)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @NotBlank
    private String name;

    @NotBlank
    private Double pricePerHour;

    @NotBlank
    private Integer floor;

    private String thumbnailUrl;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EStatus status;

    @Lob
    private String room_image;

    private String image_type;
}
