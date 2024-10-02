package de.gedoplan.demo.cachingrest.repository;

import de.gedoplan.demo.cachingrest.repository.model.Address;
import de.gedoplan.demo.cachingrest.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Random;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Random random = new Random();
        IntStream.range(0, 501).forEach(i -> {
            String username = "user" + i;
            String email = "user" + i + "@example.com";
            String password = "password" + i;
            String firstName = "First" + i;
            String lastName = "Last" + i;
            int age = random.nextInt(50) + 18;
            String phoneNumber = "+49" + (1500000000 + random.nextInt(900000000));

            var user = User.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .age(age)
                    .phoneNumber(phoneNumber)
                    .address(Address.builder()
                            .street("Main St")
                            .streetNumber(random.nextInt(10) + 1)
                            .city("Bielefeld")
                            .zipcode("33739")
                            .build())
                    .build();

            userRepository.save(user);
        });
    }
}
