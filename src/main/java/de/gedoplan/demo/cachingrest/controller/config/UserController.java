package de.gedoplan.demo.cachingrest.controller.config;

import de.gedoplan.demo.cachingrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping(path = "user/avgAge")
    public Double avgAge(){
        return userService.avgAge();
    }
}
