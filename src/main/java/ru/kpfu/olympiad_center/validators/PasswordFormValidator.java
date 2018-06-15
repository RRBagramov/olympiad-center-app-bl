package ru.kpfu.olympiad_center.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.olympiad_center.forms.PasswordForm;
import ru.kpfu.olympiad_center.models.User;
import ru.kpfu.olympiad_center.repositories.UserRepository;

import java.util.Optional;

/**
 * 09.05.2018
 *
 * @author Robert Bagramov.
 */
@Component
public class PasswordFormValidator implements Validator {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public PasswordFormValidator(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(PasswordForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForm passwordForm = (PasswordForm) target;
        passwordsValidate(passwordForm, errors);
    }

    public void passwordsValidate(PasswordForm passwordForm, Errors errors) {
        UserDetails userDetails = getUserDetailsFromSecurityContext();
        Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
        User user = optionalUser.get();
        String currentPassword = user.getPassword();
        if (!passwordEncoder.matches(passwordForm.getOldPassword(), currentPassword)) {
            errors.reject("password", "Неверный старый пароль.");
        } else if (!passwordForm.getNewPassword().equals(passwordForm.getReNewPassword())) {
            errors.reject("password", "Новый пароль не совпадает.");
        }
    }

    private UserDetails getUserDetailsFromSecurityContext() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}