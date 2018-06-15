package ru.kpfu.olympiad_center.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.olympiad_center.forms.ContactsForm;
import ru.kpfu.olympiad_center.repositories.StudentRepository;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 03.05.2018
 *
 * @author Robert Bagramov.
 */
@Component
public class ContactsFormValidator implements Validator {
    private final StudentRepository studentRepository;

    @Autowired
    public ContactsFormValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile("^((\\+?7|8)([0-9]{10}))$");
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ContactsForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContactsForm contactsForm = (ContactsForm) target;
        emailValidate(contactsForm, errors);
        phoneValidate(contactsForm, errors);
    }

    public void emailValidate(ContactsForm contactsForm, Errors errors) {
        if (!isValidEmail(contactsForm.getEmail())) {
            errors.reject("email", "Неверный формат Email");
        } else if (studentRepository.findOneByEmail(contactsForm.getEmail().toLowerCase()).isPresent())
            errors.reject("email", "Email уже зарегистрирован в системе.");
    }

    public void phoneValidate(ContactsForm contactsForm, Errors errors) {
        if (!isValidPhone(contactsForm.getPhone())) {
            errors.reject("phone", "Неверный формат номера телефона");
        }
    }

    private boolean isValidEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
