package ru.kpfu.olympiad_center.dto;

import lombok.Builder;
import lombok.Data;
import ru.kpfu.olympiad_center.models.Olympiad;
import ru.kpfu.olympiad_center.views.ExcelView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 16.04.2018
 *
 * @author Robert Bagramov.
 */
@Data
@Builder
public class OlympiadDto {
    private int id;
    private String name;
    private String organizer;
    private String country;
    private String city;
    private String level;
    private String beginDate;
    private String endDate;

    public static OlympiadDto buildFromModel(Olympiad olympiad) {
        return OlympiadDto.builder()
                .id(olympiad.getId())
                .name(olympiad.getName())
                .organizer(olympiad.getOrganizer())
                .country(olympiad.getCountry().getName())
                .city(olympiad.getCity().getName())
                .level(olympiad.getLevel().name())
                .beginDate(olympiad.getBeginDate().format(DateTimeFormatter.ofPattern(ExcelView.DATE_PATTERN)))
                .endDate(olympiad.getEndDate().format(DateTimeFormatter.ofPattern(ExcelView.DATE_PATTERN)))
                .build();
    }

    public static List<OlympiadDto> buildFromModels(List<Olympiad> olympiads) {
        return olympiads
                .stream()
                .map(OlympiadDto::buildFromModel)
                .collect(Collectors.toList());
    }
}
