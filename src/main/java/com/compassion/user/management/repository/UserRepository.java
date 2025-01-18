package com.compassion.user.management.repository;

import com.compassion.user.management.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
