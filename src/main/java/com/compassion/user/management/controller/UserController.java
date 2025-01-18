package com.compassion.user.management.controller;

import com.compassion.user.management.controller.spec.UserControllerSpec;
import com.compassion.user.management.domain.vo.UserVO;
import com.compassion.user.management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserControllerSpec {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserVO> create(@Valid @RequestBody final UserVO userVO) {
        var user = userService.create(userVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserVO>> findAll() {
        var users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/findById/{userId}")
    public ResponseEntity<UserVO> findById(@PathVariable final String userId) {
        var user = userService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserVO> update(@RequestBody final UserVO userVO) {
        var user = userService.update(userVO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/deleteById/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable final String userId) {
        userService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
