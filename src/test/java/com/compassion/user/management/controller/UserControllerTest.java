package com.compassion.user.management.controller;

import com.compassion.user.management.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.compassion.user.management.mock.UserMock.userVOMock;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreate() {
        var expected = userVOMock();
        var userVO = userVOMock();

        when(userService.create(userVO)).thenReturn(userVO);
        var usr = userController.create(userVO);

        assertThat(usr.getBody()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testFindById() {
        var expected = userVOMock();
        var userVO = userVOMock();

        when(userService.findById(userVO.id())).thenReturn(userVO);
        var usr = userController.findById(userVO.id());

        assertThat(usr.getBody()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testUpdate() {
        var expected = userVOMock();
        var userVO = userVOMock();

        when(userService.update(userVO)).thenReturn(userVO);
        var usr = userController.update(userVO);

        assertThat(usr.getBody()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testFindAll() {
        var expected = List.of(userVOMock());
        var userVO = userVOMock();

        when(userService.findAll()).thenReturn(List.of(userVO));
        var usr = userController.findAll();

        assertThat(usr.getBody()).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    void testDeleteById() {
        var userId = "e4dc13ac-388c-4bd7-b9b1-8062c3ae1205";

        doNothing().when(userService).deleteById(userId);

        userService.deleteById(userId);
        verify(userService, times(1)).deleteById(userId);

    }
}
