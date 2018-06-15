package ru.kpfu.olympiad_center.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.olympiad_center.dto.OlympiadParticipationDto;
import ru.kpfu.olympiad_center.dto.StudentDto;
import ru.kpfu.olympiad_center.dto.UserDto;
import ru.kpfu.olympiad_center.forms.EducationInfoForm;
import ru.kpfu.olympiad_center.forms.OlympiadParticipationForm;
import ru.kpfu.olympiad_center.services.OlympiadService;
import ru.kpfu.olympiad_center.services.UserService;

import java.util.List;

/**
 * 02.05.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class StudentController {
    private final UserService userService;
    private final OlympiadService olympiadService;

    @Autowired
    public StudentController(UserService userService, OlympiadService olympiadService) {
        this.olympiadService = olympiadService;
        this.userService = userService;
    }

    @PostMapping("/profile/olympiad/participated/add")
    public String addParticipatedOlympiad(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                          OlympiadParticipationForm olympiadParticipationForm) {
        userService.submitStudentOlympiadParticipation(authentication, olympiadParticipationForm);

        return "redirect:/student/olympiad/participated/all";
    }

    @GetMapping("/profile/olympiad/participated/delete")
    public String addParticipatedOlympiad(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                          @RequestParam("olympPartId") int olympiadParticipationId) {

        userService.deleteStudentOlympiadParticipationById(authentication, olympiadParticipationId);

        return "redirect:/student/olympiad/participated/all";
    }

    @GetMapping("/profile/olympiad/participated/all")
    public String getAllParticipatedOlympiads(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        List<OlympiadParticipationDto> olympiads = olympiadService.getAllParticipatedOlympiadsByAuth(authentication);
        model.addAttribute("olympiads", olympiads);

        return "page_with_olympiads";
    }
}
