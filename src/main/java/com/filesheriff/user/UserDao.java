package com.filesheriff.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("userDao")
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    User findByEmail(String email);

}
