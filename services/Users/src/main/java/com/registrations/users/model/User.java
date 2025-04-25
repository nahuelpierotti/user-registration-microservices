package com.registrations.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long idUser;

    @Column(nullable = true, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = true)
    private boolean isActive;

    @Column(nullable = true,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value=TemporalType.DATE)
    private Date created;

    @Column(nullable = true,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value=TemporalType.DATE)
    private Date lastLogin;

    @Column(nullable = true)
    @OneToMany(mappedBy = "idPhone",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Phone> phones;
}
