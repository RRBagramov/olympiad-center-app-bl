package ru.kpfu.olympiad_center.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.olympiad_center.forms.RegistrationForm;
import ru.kpfu.olympiad_center.services.RegistrationService;
import ru.kpfu.olympiad_center.validators.UserFormValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * 19.04.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class RegistrationController {
    private final RegistrationService registrationService;
    private final UserFormValidator userFormValidator;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserFormValidator userFormValidator) {
        this.registrationService = registrationService;
        this.userFormValidator = userFormValidator;
    }

    @InitBinder("userForm")
    public void initBinderUserForm(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

    @GetMapping("/registration")
    public String registrationForm(@ModelAttribute("model") ModelMap model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute("userForm") @Valid RegistrationForm form,
                                   BindingResult bindingResult,
                                   @ModelAttribute("model") ModelMap model) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                model.addAttribute(error.getCode(), error.getDefaultMessage());
            }
            return "registration";
        }

        registrationService.submitUser(form);
        return "redirect:/login";
    }
}
