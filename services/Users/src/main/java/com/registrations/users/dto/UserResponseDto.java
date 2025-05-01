package com.registrations.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    @NotNull
    private UUID id;
    @NotNull
    private Timestamp created;
    @NotNull
    private Timestamp lastLogin;

    private String  token;
    @NotNull
    private boolean isActive;
}
