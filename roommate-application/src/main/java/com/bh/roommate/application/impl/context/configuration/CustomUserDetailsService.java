package com.bh.roommate.application.impl.context.configuration;

import com.bh.roommate.application.api.model.entity.user.UserEntity;
import com.bh.roommate.application.api.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return new CustomUserDetails(user.get());
    }
}
