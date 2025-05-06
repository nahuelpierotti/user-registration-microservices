package com.registrations.users.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.UUIDJdbcType;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user_data")
public class User {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @JdbcType(UUIDJdbcType.class)
    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false)
    private UUID id=UUID.randomUUID();

    @Column(length = 150)
    private String name;

    @Column(nullable = false,unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    private boolean isActive=true;

    private Timestamp created=Timestamp.valueOf(LocalDateTime.now());

    private Timestamp lastLogin=Timestamp.valueOf(LocalDateTime.now());

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Phone> phones;

    private String token;

}
