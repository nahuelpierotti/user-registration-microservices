package com.registrations.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long idPhone;

    @Column(nullable = false)
    private long number;

    @Column(nullable = false)
    private int citycode;

    @Column(nullable = false)
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    private User user;

}
