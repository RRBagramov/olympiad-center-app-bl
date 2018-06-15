package ru.kpfu.olympiad_center.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.olympiad_center.forms.RegistrationForm;
import ru.kpfu.olympiad_center.repositories.UserRepository;

/**
 * 19.04.2018
 *
 * @author Robert Bagramov.
 */
@Component
public class UserFormValidator implements Validator {
    private final UserRepository userRepository;

    @Autowired
    public UserFormValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(RegistrationForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationForm form = (RegistrationForm) target;
        validatePassword(errors, form);
        validateLogin(errors, form);
    }

    private void validateLogin(Errors errors, RegistrationForm form) {
        if (userRepository.findOneByUsername(form.getUsername()).isPresent()) {
            errors.reject("badLogin", "Логин уже зарегистрирован в системе.");
        }
    }

    private void validatePassword(Errors errors, RegistrationForm form) {
        if (!form.getPassword().equals(form.getRepassword())) {
            errors.reject("badPassword", "Пароли не совпадают");
        }
    }
}
