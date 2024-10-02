package de.gedoplan.demo.cachingrest.service;

import de.gedoplan.demo.cachingrest.repository.UserRepository;
import de.gedoplan.demo.cachingrest.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Cacheable("avgAge")
    public Double avgAge(){
        return userRepository.findAll().stream()
                .collect(Collectors.averagingInt(User::getAge));
    }
}
