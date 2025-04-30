package com.registrations.users.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private Timestamp timestamp;
    private int code;
    private String detail;
}
