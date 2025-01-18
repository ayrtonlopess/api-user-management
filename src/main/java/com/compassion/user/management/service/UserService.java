package com.compassion.user.management.service;

import com.compassion.user.management.domain.User;
import com.compassion.user.management.domain.vo.UserVO;
import com.compassion.user.management.exception.handler.NotFoundException;
import com.compassion.user.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String ID_FIELD = "_id";

    private final MongoTemplate mongoTemplate;
    private final UserRepository userRepository;

    protected final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserVO create(final UserVO userVO) {
        log.info("Starting the user creation process.");
        var user = User.of(userVO);
        log.info("The user has been prepared successfully.");
        var userSaved = userRepository.save(user);
        log.info("The user has been saved successfully: {}", userSaved);
        return userVO;
    }

    public UserVO findById(final String userId) {
        log.info("Starting the user find process.");
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        log.info("User was found successfully.");
        return UserVO.of(user);
    }

    public UserVO update(final UserVO userVO) {
        log.info("Starting the user update process.");
        final var idUser = userVO.id();

        var query = new Query(Criteria.where(ID_FIELD)
                .is(idUser));

        var update = new Update()
                .set("name", userVO.name())
                .set("email", userVO.email())
                .set("phone", userVO.phone())
                .set("document", userVO.document())
                .set("documentType", userVO.documentType())
                .set("userType", userVO.userType())
                .set("description", userVO.description())
                .set("neighborhood", userVO.neighborhood())
                .set("updateDate", LocalDateTime.now());

        var user = mongoTemplate.update(User.class)
                .matching(query)
                .apply(update)
                .withOptions(FindAndModifyOptions.options().returnNew(true))
                .findAndModifyValue();

        log.info("User was updated successfully.");

        return UserVO.of(user);
    }

    public List<UserVO> findAll() {
        log.info("Starting the get all users process.");
        var allUsers = userRepository.findAll();
        log.info("Users were found successfully.");
        return UserVO.of(allUsers);
    }

    public void deleteById(final String userId) {
        log.info("Starting the user delete process.");
        userRepository.deleteById(userId);
        log.info("User was deleted successfully.");
    }
}
