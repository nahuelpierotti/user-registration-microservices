package com.registrations.users.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idPhone;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private Integer citycode;

    @Column(nullable = false)
    private String countrycode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser",nullable = false,foreignKey = @ForeignKey(name = "FK_USER_PHONES"))
    private User user;

}
