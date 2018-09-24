package ru.otus.dz19.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dz19.domain.User;
import ru.otus.dz19.repository.UserRepository;

import javax.annotation.PostConstruct;

//@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = userRepository.findByName(name);

//        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(name);
//            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.password(user.getPassword());
            builder.roles(user.getRole());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

//    @PostConstruct
//    public void init(){
//        User user = new User();
//        user.setName("admin");
//        user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
//        user.setRole("USER");
//        userRepository.save(user);
//    }
}
