package ru.kpfu.olympiad_center.services;

import ru.kpfu.olympiad_center.forms.RegistrationForm;

/**
 * 18.04.2018
 *
 * @author Robert Bagramov.
 */
public interface RegistrationService {
    void submitUser(RegistrationForm registrationForm);
}
