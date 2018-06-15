package ru.kpfu.olympiad_center.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kpfu.olympiad_center.dto.OlympiadDto;
import ru.kpfu.olympiad_center.dto.OlympiadParticipationDto;
import ru.kpfu.olympiad_center.exceptions.OlympiadNotFoundEcxeption;
import ru.kpfu.olympiad_center.forms.OlympiadForm;
import ru.kpfu.olympiad_center.models.Olympiad;
import ru.kpfu.olympiad_center.models.Student;
import ru.kpfu.olympiad_center.models.enums.OlympiadLevel;
import ru.kpfu.olympiad_center.repositories.OlympiadParticipationRepository;
import ru.kpfu.olympiad_center.repositories.OlympiadRepository;
import ru.kpfu.olympiad_center.repositories.StudentRepository;
import ru.kpfu.olympiad_center.services.OlympiadService;
import ru.kpfu.olympiad_center.views.ExcelView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 04.05.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class OlympiadServiceImpl implements OlympiadService {
    private final OlympiadRepository olympiadRepository;
    private final StudentRepository studentRepository;
    private final OlympiadParticipationRepository olympiadParticipationRepository;

    @Autowired
    public OlympiadServiceImpl(OlympiadRepository olympiadRepository,
                               OlympiadParticipationRepository olympiadParticipationRepository,
                               StudentRepository studentRepository) {
        this.olympiadParticipationRepository = olympiadParticipationRepository;
        this.olympiadRepository = olympiadRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<OlympiadDto> getAllOlympiads() {
        List<Olympiad> olympiads = (List<Olympiad>) olympiadRepository.findAll();
        return OlympiadDto.buildFromModels(olympiads);
    }

    @Override
    public void submitOlympiad(OlympiadForm olympiadForm) {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(ExcelView.DATE_PATTERN);
        LocalDate beginDate = LocalDate.parse(olympiadForm.getBeginDate(), datePattern);
        LocalDate endDate = LocalDate.parse(olympiadForm.getEndDate(), datePattern);

        Olympiad olympiad = Olympiad.builder()
                .name(olympiadForm.getName())
                .level(OlympiadLevel.valueOf(olympiadForm.getLevel()))
                .country(olympiadForm.getCountry())
                .city(olympiadForm.getCity())
                .beginDate(beginDate)
                .endDate(endDate)
                .build();

        olympiadRepository.save(olympiad);
    }

    @Override
    public void deleteOlympiadById(int olympiadId) {
        olympiadRepository.deleteById(olympiadId);
    }

    @Override
    public List<OlympiadParticipationDto> getAllParticipatedOlympiadsByAuth(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<Student> optionalStudent = studentRepository.findOneByUsername(userDetails.getUsername());
        return OlympiadParticipationDto.buildFromModels(olympiadParticipationRepository.findAllByStudent(optionalStudent.get()));
    }

    @Override
    public OlympiadDto findOlympiadById(int olympiadId) {
        Optional<Olympiad> olympiadById = olympiadRepository.findById(olympiadId);
        return OlympiadDto
                .buildFromModel(olympiadById
                        .orElseThrow(() -> new OlympiadNotFoundEcxeption("Olympiad not found by id " + olympiadId)));
    }

    @Override
    public List<Olympiad> findAllOlympiadsBetweenDates(LocalDate beginDate, LocalDate endDate) {
        Optional<List<Olympiad>> optionalOlypmiads =
                olympiadRepository.findAllByBeginDateGreaterThanEqualAndEndDateLessThanEqual(beginDate, endDate);

        return optionalOlypmiads.orElse((List<Olympiad>) Collections.EMPTY_LIST);
    }
}
