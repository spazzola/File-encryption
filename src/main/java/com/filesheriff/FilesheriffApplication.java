package com.filesheriff;

import com.filesheriff.user.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableJpaRepositories(basePackageClasses = UserDao.class)
public class FilesheriffApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesheriffApplication.class, args);
	}

}
