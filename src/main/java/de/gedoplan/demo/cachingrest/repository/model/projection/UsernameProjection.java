package de.gedoplan.demo.cachingrest.repository.model.projection;

import de.gedoplan.demo.cachingrest.repository.model.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "username", types = { User.class })
public interface UsernameProjection {

    String getUsername();
}
