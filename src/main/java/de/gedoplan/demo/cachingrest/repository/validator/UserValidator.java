package de.gedoplan.demo.cachingrest.repository.validator;

import de.gedoplan.demo.cachingrest.repository.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Configuration
public class UserValidator implements RepositoryRestConfigurer {

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        var userPhoneValidator = new UserPhoneAgeValidator();
        validatingListener.addValidator("beforeSave", userPhoneValidator);
        validatingListener.addValidator("beforeCreate", userPhoneValidator);
    }

    private static class UserPhoneAgeValidator implements Validator{
        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            if(user.getAge()>18 && !StringUtils.hasText(user.getPhoneNumber())){
                errors.rejectValue("phoneNumber", "phoneNumber.invalid");
            }
        }
    }
}
