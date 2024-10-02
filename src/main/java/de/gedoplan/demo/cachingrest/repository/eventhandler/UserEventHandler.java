package de.gedoplan.demo.cachingrest.repository.eventhandler;

import de.gedoplan.demo.cachingrest.repository.model.User;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UserEventHandler {
    @HandleBeforeSave
    public void handleBeforeSave(User user) {
        // let's make our users a little bit younger
        user.setAge(user.getAge() - 10);
    }
}
