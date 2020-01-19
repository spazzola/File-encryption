package com.filesheriff.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfiguration {

    @Value("${encryption.key}")
    public String key;

    @Value("${encryption.algorithm}")
    public String algorithm;

}