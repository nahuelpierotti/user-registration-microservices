package com.registrations.users.service;

import com.registrations.users.model.User;
import com.registrations.users.repo.IUserRepository;
import com.registrations.users.service.impl.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class JwtServiceTests {

    @Mock
    public IUserRepository userRepository;

    private JwtService jwtService;

    private User userMock=null;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        jwtService=new JwtService(userRepository);
        jwtService.setSecretKey("da0c07d2368ef7b845a8c1577bfd0d390bdd568dad364b9b9a4e2ca0c9c2f90f817595f6513334c7b9d92c8ee4d80d47f6a933d8ed00193ee51bbeaeec4144c7");
         userMock = User.builder()
            .email("john.doe@example.com")
            .password("a2asfGfdfdf4")
            .name("John Doe")
            .build();
    }

    @Test
    @DisplayName("Generating a token")
    public void generateAToken() {
        String tokenCreated=jwtService.buildToken(userMock);
        Assertions.assertNotNull(tokenCreated);
    }

    @Test
    @DisplayName("Obtain email from token")
    public void obtainEmailFromToken() {
        String tokenCreated=jwtService.buildToken(userMock);
        String userName=jwtService.getUsernameFromToken(tokenCreated);
        Assertions.assertEquals(userName,userMock.getEmail());
    }
}
