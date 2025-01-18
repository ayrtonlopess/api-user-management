package com.compassion.user.management.service;

import com.compassion.user.management.domain.User;
import com.compassion.user.management.domain.enums.DocumentType;
import com.compassion.user.management.domain.enums.UserType;
import com.compassion.user.management.domain.vo.UserVO;
import com.compassion.user.management.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreate() {
        var expected = userVOMock();
        var user = userMock();
        var userVO = userVOMock();

        when(userRepository.save(any(User.class))).thenReturn(user);
        var usr = userService.create(userVO);

        assertThat(usr).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    void testFindById() {
        var userId = "e4dc13ac-388c-4bd7-b9b1-8062c3ae1205";
        var expected = userVOMock();
        var user = userMock();

        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));
        var usr = userService.findById(userId);

        assertThat(usr).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    void testUpdate() {
        UserVO userVO = userVOMock();
        User mockUser = userMock();

        var query = new Query(Criteria.where("_id")
                .is(userVO.id()));

        var executableUpdateMock = mock(ExecutableUpdateOperation.ExecutableUpdate.class);
        var terminatingUpdateMock = mock(ExecutableUpdateOperation.TerminatingUpdate.class);


        when(mongoTemplate.update(eq(User.class)))
                .thenReturn(executableUpdateMock);

        when(executableUpdateMock.matching(eq(query))).thenReturn(executableUpdateMock);
        when(executableUpdateMock.apply(any())).thenReturn(terminatingUpdateMock);
        when(terminatingUpdateMock.withOptions(any()))
                .thenReturn(terminatingUpdateMock);
        when(terminatingUpdateMock.findAndModifyValue()).thenReturn(mockUser);

        var updatedUserVO = userService.update(userVO);

        assertNotNull(updatedUserVO);

        verify(mongoTemplate).update(eq(User.class));
        verify(executableUpdateMock).apply(any());
        verify(terminatingUpdateMock).withOptions(any());
        verify(terminatingUpdateMock).findAndModifyValue();
    }

    @Test
    void testGetAll() {
        var expected = List.of(userVOMock());
        var userList = List.of(userMock());

        when(userRepository.findAll()).thenReturn(userList);
        var userVOList = userService.findAll();

        assertThat(userVOList).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    void testDeleteById() {
        var userId = "e4dc13ac-388c-4bd7-b9b1-8062c3ae1205";

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteById(userId);
        verify(userRepository, times(1)).deleteById(userId);

    }

    private User userMock() {
        return User.builder()
                .id("e4dc13ac-388c-4bd7-b9b1-8062c3ae1205")
                .neighborhood("Vila Emilio")
                .active(true)
                .email("teste@gmail.com")
                .name("Teste LTDA")
                .phone("11945756561")
                .userType(UserType.ENTREPRENEUR)
                .verified(true)
                .document("45644588867")
                .documentType(DocumentType.CNPJ)
                .description("The Best Company of make you beautiful")
                .build();
    }

    private UserVO userVOMock() {
        return UserVO.of(userMock());
    }

}
