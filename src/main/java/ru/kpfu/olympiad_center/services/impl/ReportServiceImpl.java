package ru.kpfu.olympiad_center.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.olympiad_center.dto.OlympiadDto;
import ru.kpfu.olympiad_center.dto.StudentDto;
import ru.kpfu.olympiad_center.models.Olympiad;
import ru.kpfu.olympiad_center.services.OlympiadService;
import ru.kpfu.olympiad_center.services.ReportService;
import ru.kpfu.olympiad_center.views.ExcelView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static padeg.lib.Padeg.getAppointmentPadeg;
import static ru.kpfu.olympiad_center.util.RussianLanguageCases.GENITIVE_CASE;

/**
 * 10.06.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OlympiadService olympiadService;

    @Override
    public List<Olympiad> findAllOlympiadsBetweenDates(String startDate, String endDate) {

        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(ExcelView.DATE_PATTERN);
        LocalDate localStartDate = LocalDate.parse(startDate, datePattern);
        LocalDate localEndDate = LocalDate.parse(endDate, datePattern);

        return olympiadService.findAllOlympiadsBetweenDates(localStartDate, localEndDate);
    }

    @Override
    public Map<String, String> getRecommendationLetterParams(StudentDto student, OlympiadDto olympiad) {
        Map<String, String> params = new HashMap<>();
        String lastName = getAppointmentPadeg(student.getLastName(), GENITIVE_CASE);
        String firstName = getAppointmentPadeg(student.getFirstName(), GENITIVE_CASE);
        String middleName = getAppointmentPadeg(student.getMiddleName(), GENITIVE_CASE);

        params.put("lastName", lastName);
        params.put("firstName", firstName);
        params.put("middleName", middleName);

        params.put("lastName2", lastName);
        params.put("firstName2", firstName);
        params.put("middleName2", middleName);

        params.put("olympiad", olympiad.getName());
        params.put("city", olympiad.getCity());

        params.put("beginDate", olympiad.getBeginDate());
        params.put("endDate", olympiad.getBeginDate());

        return params;
    }

    @Override
    public Map<String, String> getParticipationReportParams(StudentDto student, OlympiadDto olympiad) {
        Map<String, String> params = new HashMap<>();

        params.put("lastName", getAppointmentPadeg(student.getLastName(), GENITIVE_CASE));
        params.put("firstName", getAppointmentPadeg(student.getFirstName(), GENITIVE_CASE));
        params.put("middleName", getAppointmentPadeg(student.getMiddleName(), GENITIVE_CASE));

        params.put("institute", student.getInstitute());
        params.put("groupNumber", student.getGroupNumber());
        params.put("olympiad", olympiad.getName());
        params.put("city", olympiad.getCity());
        params.put("organizer", olympiad.getOrganizer());
        params.put("beginDate", olympiad.getBeginDate());
        params.put("endDate", olympiad.getBeginDate());

        return params;
    }

    @Override
    public Map<String, String> getApplicationRationalReportParams(StudentDto student) {
        Map<String, String> params = new HashMap<>();
        params.put("firstName", student.getFirstName());
        params.put("lastName", student.getLastName());
        params.put("middleName", student.getMiddleName());
        params.put("groupNumber", student.getGroupNumber());
        params.put("institute", student.getInstitute());
        params.put("email", student.getEmail());
        params.put("phone", student.getPhone());
        params.put("birthDate", student.getBirthDate());

        return params;
    }
}
