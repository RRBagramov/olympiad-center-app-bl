package ru.kpfu.olympiad_center.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.olympiad_center.forms.RegistrationForm;
import ru.kpfu.olympiad_center.models.User;
import ru.kpfu.olympiad_center.repositories.UserRepository;
import ru.kpfu.olympiad_center.security.role.Role;
import ru.kpfu.olympiad_center.services.RegistrationService;

/**
 * 19.04.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void submitUser(RegistrationForm registrationForm) {
        User user = User.builder()
                .firstName(registrationForm.getFirstName())
                .middleName(registrationForm.getMiddleName())
                .lastName(registrationForm.getLastName())
                .username(registrationForm.getUsername())
                .password(encoder.encode(registrationForm.getPassword()))
                .role(Role.STUDENT)
                .build();

        userRepository.save(user);
    }
}
