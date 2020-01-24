package com.filesheriff.userdetails;

import com.filesheriff.user.User;
import com.filesheriff.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByUserName(name);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + name));

        return user.map(MyUserDetails::new).get();
    }
}
