package com.registrations.users.service;

import com.registrations.users.model.User;
import com.registrations.users.repo.IUserRepository;
import com.registrations.users.service.impl.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class JwtServiceTests {

    @Autowired
    public IUserRepository userRepository;

    @MockitoBean
    private JwtService jwtService;

    private User userMock=null;

    @BeforeEach
    public void setUp() {
         userMock = User.builder()
            .email("john.doe@example.com")
            .password("a2asfGfdfdf4")
            .build();
    }

    @Test
    @DisplayName("Generating a token")
    public void generateToken() {
        String tokenCreated=jwtService.buildToken(userMock);
        Assertions.assertNotNull(tokenCreated);
    }
}
