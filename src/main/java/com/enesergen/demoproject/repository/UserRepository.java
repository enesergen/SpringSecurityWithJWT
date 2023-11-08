package com.enesergen.demoproject.repository;

import com.enesergen.demoproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByObjId(String objId);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
