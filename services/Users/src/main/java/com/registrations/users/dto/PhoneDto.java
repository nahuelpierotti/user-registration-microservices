package com.registrations.users.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto
{
    @JsonBackReference
    private UserRequestDto user;

    private long number;

    private int citycode;

    private String countrycode;

}
