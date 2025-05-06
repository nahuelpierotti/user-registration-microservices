package com.registrations.users.repository;

import com.registrations.users.model.User;
import com.registrations.users.repo.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;


public class UserRepositoryTests {


    @Mock
    private IUserRepository userRepository;
    User userMock=null;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userMock= User.builder()
                .idUser(Long.parseLong("2"))
                .name("John Doe")
                .email("john.doe@example.com")
                .password("a2asfGfdfdf4")
                .build();


    }
    @Test
    @DisplayName("Adding successfully a user")
    void addAUser(){
        userRepository.save(userMock);
    }


    @Test
    @DisplayName("Retrieving a user from email")
    public void findOneByEmail() {
        when(userRepository.findOneByEmail("john.doe@example.com")).thenReturn(userMock);
        Assertions.assertThat(userMock).isNotNull();
    }

    @AfterEach
    public void tearDown() {
        userRepository.delete(userMock);
    }
}
