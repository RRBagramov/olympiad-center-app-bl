package ru.kpfu.olympiad_center.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.olympiad_center.dto.OlympiadDto;
import ru.kpfu.olympiad_center.forms.OlympiadForm;
import ru.kpfu.olympiad_center.services.OlympiadService;

import java.util.List;

/**
 * 06.05.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class OlympiadController {
    private final OlympiadService olympiadService;

    @Autowired
    public OlympiadController(OlympiadService olympiadService) {
        this.olympiadService = olympiadService;
    }

    @PostMapping("/olympiad/add")
    public String addOlympiad(@ModelAttribute("model") ModelMap modelMap, OlympiadForm olympiadForm) {
        olympiadService.submitOlympiad(olympiadForm);

        return "page_with_olympiads";
    }

    @GetMapping("/olympiad/delete")
    public String deleteOlympiad(@ModelAttribute("model") ModelMap model,
                                 @RequestParam("olympiadId") int olympiadId) {

        olympiadService.deleteOlympiadById(olympiadId);
        return "page_with_olympiads";
    }

    @GetMapping("/olympiad/all")
    public String getAllOlympiads(@ModelAttribute("model") ModelMap model) {
        List<OlympiadDto> olympiads = olympiadService.getAllOlympiads();
        model.addAttribute("olympiads", olympiads);

        return "page_with_olympiads";
    }

}
