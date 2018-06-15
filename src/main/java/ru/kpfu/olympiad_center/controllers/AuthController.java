package ru.kpfu.olympiad_center.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.olympiad_center.services.AuthService;

import java.util.Optional;

/**
 * 01.05.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"/", "/login"})
    public String login(@ModelAttribute("model") ModelMap model,
                        Authentication authentication,
                        @RequestParam Optional<String> error) {

        if (authentication != null) {
            return profilePageUrl(authentication);
        }
        model.addAttribute("error", error);
        return "login";
    }

    private String profilePageUrl(Authentication authentication) {
        switch (authService.getCurrentUserRole(authentication)) {
            case "ADMIN":
                return "test_profile";
            case "TEACHER":
                return "test_profile";
            case "STUDENT":
                return "redirect:/student/profile";
            default:
                throw new IllegalArgumentException("Incorrect role");
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
