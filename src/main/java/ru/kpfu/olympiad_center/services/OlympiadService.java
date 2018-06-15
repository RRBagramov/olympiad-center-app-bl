package ru.kpfu.olympiad_center.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.olympiad_center.dto.OlympiadDto;
import ru.kpfu.olympiad_center.dto.OlympiadParticipationDto;
import ru.kpfu.olympiad_center.forms.OlympiadForm;
import ru.kpfu.olympiad_center.models.Olympiad;

import java.time.LocalDate;
import java.util.List;

/**
 * 18.04.2018
 *
 * @author Robert Bagramov.
 */
public interface OlympiadService {
    List<OlympiadDto> getAllOlympiads();

    void deleteOlympiadById(int olympiadId);

    void submitOlympiad(OlympiadForm olympiadForm);

    List<OlympiadParticipationDto> getAllParticipatedOlympiadsByAuth(Authentication authentication);

    OlympiadDto findOlympiadById(int olympiadId);

    List<Olympiad> findAllOlympiadsBetweenDates(LocalDate beginDate, LocalDate endDate);
}
