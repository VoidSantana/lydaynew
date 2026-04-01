package com.example.santanadev.lydaynew.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "branches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;
}
