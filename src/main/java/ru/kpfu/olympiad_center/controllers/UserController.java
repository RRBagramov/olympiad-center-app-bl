package ru.kpfu.olympiad_center.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.olympiad_center.dto.StudentDto;
import ru.kpfu.olympiad_center.dto.UserDto;
import ru.kpfu.olympiad_center.forms.BaseInfoForm;
import ru.kpfu.olympiad_center.forms.ContactsForm;
import ru.kpfu.olympiad_center.forms.EducationInfoForm;
import ru.kpfu.olympiad_center.forms.PasswordForm;
import ru.kpfu.olympiad_center.security.role.Role;
import ru.kpfu.olympiad_center.services.UserService;
import ru.kpfu.olympiad_center.validators.ContactsFormValidator;
import ru.kpfu.olympiad_center.validators.PasswordFormValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * 04.05.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class UserController {
    private final UserService userService;
    private final ContactsFormValidator contactsFormValidator;
    private final PasswordFormValidator passwordFormValidator;

    @Autowired
    public UserController(UserService userService, ContactsFormValidator contactsFormValidator, PasswordFormValidator passwordFormValidator) {
        this.userService = userService;
        this.contactsFormValidator = contactsFormValidator;
        this.passwordFormValidator = passwordFormValidator;
    }

    @InitBinder("contactsForm")
    public void initBinderContactsForm(WebDataBinder binder) {
        binder.addValidators(contactsFormValidator);
    }

    @InitBinder("passwordForm")
    public void initBinderPasswordForm(WebDataBinder binder) {
        binder.addValidators(passwordFormValidator);
    }

    @GetMapping("/profile")
    public String studentPage(@ModelAttribute("model") ModelMap model,
                              Authentication authentication) {

        UserDto userDto = userService.getUserByAuth(authentication);
        model.addAttribute("user", userDto);
        if (authentication.getAuthorities().contains(Role.TEACHER.name())) {
            return "teacher_profile_page";
        }

        return "student_profile_page";
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/profile/education/update")
    public String submitEducation(@ModelAttribute("model") ModelMap model,
                                  EducationInfoForm educationForm,
                                  Authentication authentication) {

        userService.submitStudentProfileEducation(authentication, educationForm);
        StudentDto studentDto = userService.getStudentByAuth(authentication);
        model.addAttribute("user", studentDto);

        return "student_page";
    }

    @PostMapping("/profile/base/update")
    public String updateBaseInformation(@ModelAttribute("model") ModelMap model,
                                        @Valid BaseInfoForm baseInfoForm, BindingResult bindingResult,
                                        Authentication authentication) {

        if (bindingResult.hasErrors()) {
            addErrorsFromBindingResultToModel(bindingResult, model);
            return "user_page";
        }

        userService.submitUserProfileBase(authentication, baseInfoForm);
        StudentDto studentDto = userService.getStudentByAuth(authentication);
        model.addAttribute("user", studentDto);

        return "user_page";
    }

    @PostMapping("/profile/contacts/update")
    public String submitContacts(@ModelAttribute("model") ModelMap model,
                                 @Valid ContactsForm contactsForm, BindingResult bindingResult,
                                 Authentication authentication) {

        if (bindingResult.hasErrors()) {
            addErrorsFromBindingResultToModel(bindingResult, model);
            return "user_page";
        }

        userService.submitUserProfileContacts(authentication, contactsForm);
        StudentDto studentDto = userService.getStudentByAuth(authentication);
        model.addAttribute("user", studentDto);

        return "user_page";
    }

    @PostMapping("/profile/password/update")
    public String submitPassword(@Valid PasswordForm passwordForm, BindingResult bindingResult,
                                 @ModelAttribute("model") ModelMap model,
                                 Authentication authentication) {

        if (bindingResult.hasErrors()) {
            addErrorsFromBindingResultToModel(bindingResult, model);
            return "user_page";
        }

        userService.submitUserProfilePassword(authentication, passwordForm);

        return "user_page";
    }

    @GetMapping("/students/all")
    public String getAllStudents(@ModelAttribute("model") ModelMap model) {
        List<StudentDto> students = userService.getAllStudents();
        model.addAttribute("students", students);

        return "page_with_students";
    }

    private void addErrorsFromBindingResultToModel(BindingResult bindingResult, ModelMap model) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            model.addAttribute(error.getCode(), error.getDefaultMessage());
        }
    }


}
