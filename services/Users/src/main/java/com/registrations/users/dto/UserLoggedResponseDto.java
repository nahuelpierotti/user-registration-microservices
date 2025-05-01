package com.registrations.users.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.registrations.users.model.Phone;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoggedResponseDto {
    @NotNull
    private UUID id;
    @NotNull
    private Timestamp created;
    @NotNull
    private Timestamp lastLogin;
    @NotNull
    private String token;
    @NotNull
    private boolean isActive;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @JsonBackReference
    private List<Phone> phones;

    @JsonProperty("phone")
    private List<PhoneDto> userPhoneList;
}
