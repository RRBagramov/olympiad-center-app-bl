package ru.kpfu.olympiad_center.services;


import ru.kpfu.olympiad_center.dto.OlympiadDto;
import ru.kpfu.olympiad_center.dto.StudentDto;
import ru.kpfu.olympiad_center.models.Olympiad;

import java.util.List;
import java.util.Map;

/**
 * 10.06.2018
 *
 * @author Robert Bagramov.
 */
public interface ReportService {
    List<Olympiad> findAllOlympiadsBetweenDates(String startDate, String endDate);

    Map<String, String> getRecommendationLetterParams(StudentDto student, OlympiadDto olympiad);

    Map<String, String> getParticipationReportParams(StudentDto student, OlympiadDto olympiad);

    Map<String, String> getApplicationRationalReportParams(StudentDto student);
}
