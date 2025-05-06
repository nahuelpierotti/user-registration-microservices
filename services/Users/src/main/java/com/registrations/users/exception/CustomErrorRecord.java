package com.registrations.users.exception;

import java.sql.Timestamp;


public record CustomErrorRecord(
        Timestamp timestamp,
        int code,
        String details
) {
}
