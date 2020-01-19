package com.filesheriff.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository("userDao")
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    User findByEmail(String email);
    //User findByUserName(String name);

    @Transactional
    @Modifying
    @Query("Update User u set key_id = ?1 where u.id = ?2")
    void setKey(long keyId, long userId);

}
