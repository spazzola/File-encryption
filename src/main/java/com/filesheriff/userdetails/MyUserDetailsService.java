package com.filesheriff.userdetails;

import com.filesheriff.form.LoginForm;
import com.filesheriff.user.User;
import com.filesheriff.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;


    public MyUserDetailsService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        final User user = userDao.findByUserName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + name));

        return new MyUserDetails(user);
    }

    public boolean login(LoginForm loginForm) {
        final UserDetails userDetails = loadUserByUsername(loginForm.getUserName());
        return passwordEncoder.matches(loginForm.getPassword(), userDetails.getPassword());
    }
}
