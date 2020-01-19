package com.filesheriff.key;

import com.filesheriff.user.User;

import javax.crypto.SecretKey;
import javax.persistence.*;

@Entity
public class Key {

    @Id
    @Column(name = "key_id")
    private long keyId;

    private SecretKey secretKey;

    @OneToOne(mappedBy = "key")
    private User user;


    public Key() {

    }

    public Key(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public Key(Long keyId, SecretKey secretKey ) {
        this.keyId = keyId;
        this.secretKey = secretKey;
    }

}
