package com.registrations.users.repository;

import com.registrations.users.model.Phone;
import com.registrations.users.model.User;
import com.registrations.users.repo.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;


@JdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {


    @Autowired
    private IUserRepository userRepository;
    User userMock=null;

    @BeforeEach
    public void setUp() {
        userMock= User.builder()
                .idUser(Long.parseLong("2"))
                .name("John Doe")
                .email("john.doe@example.com")
                .password("a2asfGfdfdf4")
                .build();

        Phone phone= Phone.builder()
                .idPhone(Long.parseLong("1"))
                .number(Long.parseLong("1150554949"))
                .citycode(1)
                .countrycode("54")
                .user(userMock)
                .build();

        userRepository.save(userMock);
    }

    @Test
    @DisplayName("Retrieving a user from email")
    public void findOneByEmail() {
        Assertions.assertThat(userRepository.findOneByEmail(userMock.getEmail())).isNotNull();
    }

    @AfterEach
    public void tearDown() {
        userRepository.delete(userMock);
    }
}
