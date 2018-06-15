package ru.kpfu.olympiad_center.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.olympiad_center.dto.OlympiadDto;
import ru.kpfu.olympiad_center.dto.StudentDto;
import ru.kpfu.olympiad_center.models.Olympiad;
import ru.kpfu.olympiad_center.repositories.OlympiadRepository;
import ru.kpfu.olympiad_center.services.OlympiadService;
import ru.kpfu.olympiad_center.services.ReportService;
import ru.kpfu.olympiad_center.services.UserService;
import ru.kpfu.olympiad_center.settings.ReportSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static padeg.lib.Padeg.getAppointmentPadeg;
import static ru.kpfu.olympiad_center.settings.ReportSettings.*;

/**
 * 20.05.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class ReportController {
    private final UserService userService;
    private final ReportSettings reportSettings;
    private final OlympiadService olympiadService;
    private final ReportService reportService;

    @Autowired
    public ReportController(UserService userService, ReportSettings reportSettings, OlympiadService olympiadService, ReportService reportService) {
        this.userService = userService;
        this.reportSettings = reportSettings;
        this.olympiadService = olympiadService;
        this.reportService = reportService;
    }

    @GetMapping("/reports/common/type")
    public void getCommonReport(Model model,
                                @RequestParam(value = "startDate", required = false) String startDate,
                                @RequestParam(value = "endDate", required = false) String endDate) {

        List<Olympiad> allOlympiadsBetweenDates = reportService.findAllOlympiadsBetweenDates(startDate, endDate);

        model.addAttribute("olympiads", allOlympiadsBetweenDates);
    }

    @GetMapping("/reports/{reportTypeNumber}")
    public void getStudentReport(
            Model model,
            Authentication authentication,
            @PathVariable("reportTypeNumber") int reportTypeNumber,
            @RequestParam(value = "olympiadId", required = false) int olympiadId) {

        switch (reportTypeNumber) {
            case APPLICATION_RATIONAL_REPORT:
                generateApplicationRationalReport(model, authentication);
                break;
            case PARTICIPATION_REPORT:
                generateParticipationReport(model, authentication, olympiadId);
                break;
            case RECOMMENDATION_LETTER:
                generateRecommendationLetter(model, authentication, olympiadId);
                break;
        }
    }

    private void generateApplicationRationalReport(Model model, Authentication authentication) {
        StudentDto student = userService.getStudentByAuth(authentication);
        model.addAttribute("filepath", reportSettings.getApplicationRationalePath());
        model.addAttribute("bookmarks", reportService.getApplicationRationalReportParams(student));
    }

    private void generateParticipationReport(Model model, Authentication authentication, int olympiadId) {
        StudentDto student = userService.getStudentByAuth(authentication);
        OlympiadDto olympiad = olympiadService.findOlympiadById(olympiadId);

        model.addAttribute("filepath", reportSettings.getParticipationPath());
        model.addAttribute("bookmarks", reportService.getParticipationReportParams(student, olympiad));
    }

    private void generateRecommendationLetter(Model model, Authentication authentication, int olympiadId) {
        StudentDto student = userService.getStudentByAuth(authentication);
        OlympiadDto olympiad = olympiadService.findOlympiadById(olympiadId);

        model.addAttribute("filepath", reportSettings.getRecommendationLetterPath());
        model.addAttribute("bookmarks", reportService.getRecommendationLetterParams(student, olympiad));
    }
}
