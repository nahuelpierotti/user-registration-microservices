package com.registrations.users.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDto
{
    @JsonBackReference
    private UserRequestDto user;

    private Long number;

    private Integer citycode;

    private String countrycode;

}
