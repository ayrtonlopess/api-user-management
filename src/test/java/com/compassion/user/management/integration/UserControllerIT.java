package com.compassion.user.management.integration;

import com.compassion.user.management.domain.User;
import com.compassion.user.management.domain.enums.DocumentType;
import com.compassion.user.management.domain.enums.UserType;
import com.compassion.user.management.domain.vo.UserVO;
import com.compassion.user.management.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.compassion.user.management.mock.UserMock.userMock;
import static com.compassion.user.management.mock.UserMock.userVOMock;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    private static final String PATH_USER = "/user";


    @Test
    void testCreate() {
        var userMock = userVOMock();
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var requestEntity = new HttpEntity<>(userMock, headers);

        var response = testRestTemplate.exchange(
                PATH_USER + "/create",
                HttpMethod.POST,
                requestEntity,
                UserVO.class
        );

        assertThat(response.getStatusCode()).usingRecursiveComparison().isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(userMock);
    }

    @Test
    void testFindById() {
        var user = userRepository.save(userMock());
        var userMock = userVOMock();

        var exchange = testRestTemplate.exchange(
                PATH_USER + "/findById/" + user.getId(),
                HttpMethod.GET,
                null,
                UserVO.class
        );

        assertThat(exchange.getStatusCode()).usingRecursiveComparison().isEqualTo(HttpStatus.OK);
        assertThat(exchange.getBody()).usingRecursiveComparison().isEqualTo(userMock);
    }

    @Test
    void testFindAll() {
        userRepository.save(userMock());

        var exchange = testRestTemplate.exchange(
                PATH_USER + "/findAll",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        assertThat(exchange.getStatusCode()).usingRecursiveComparison().isEqualTo(HttpStatus.OK);
        assertThat(exchange.hasBody()).isEqualTo(true);
    }

    @Test
    void testUpdate() {
        userRepository.save(userMock());

        var updatedUser = User.builder()
                .id("e4dc13ac-388c-4bd7-b9b1-8062c3ae1205")
                .neighborhood("Vila Emilio")
                .active(true)
                .email("teste@gmail.com")
                .name("Teste LTDA")
                .phone("11945756561")
                .userType(UserType.ENTREPRENEUR)
                .verified(true)
                .document("45644588867")
                .documentType(DocumentType.CPF)
                .description("The Best Company of make you beautiful")
                .build();

        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var requestEntity = new HttpEntity<>(updatedUser, headers);

        var response = testRestTemplate.exchange(
                PATH_USER + "/update",
                HttpMethod.PUT,
                requestEntity,
                UserVO.class
        );

        assertThat(response.getStatusCode()).usingRecursiveComparison().isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(updatedUser);
    }

    @Test
    void testDeleteById() {
        var user = userRepository.save(userMock());

        var exchange = testRestTemplate.exchange(
                PATH_USER + "/deleteById/" + user.getId(),
                HttpMethod.DELETE,
                null,
                UserVO.class
        );

        assertThat(exchange.getStatusCode()).usingRecursiveComparison().isEqualTo(HttpStatus.NO_CONTENT);
    }
}
