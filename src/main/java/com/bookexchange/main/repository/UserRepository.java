package com.bookexchange.main.repository;

import com.bookexchange.main.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByEmail(String Email);
    Optional<User> findByToken(String token);
}
