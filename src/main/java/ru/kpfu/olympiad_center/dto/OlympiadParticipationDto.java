package ru.kpfu.olympiad_center.dto;

import lombok.Builder;
import lombok.Data;
import ru.kpfu.olympiad_center.models.OlympiadParticipation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 16.04.2018
 *
 * @author Robert Bagramov.
 */
@Data
@Builder
public class OlympiadParticipationDto {
    private int id;
    private StudentDto student;
    private OlympiadDto olympiad;
    private String participationResult;

    public static OlympiadParticipationDto buildFromModel(OlympiadParticipation olympiadParticipation) {
        return OlympiadParticipationDto.builder()
                .id(olympiadParticipation.getId())
                .student(StudentDto.buildFromModel(olympiadParticipation.getStudent()))
                .olympiad(OlympiadDto.buildFromModel(olympiadParticipation.getOlympiad()))
                .participationResult(olympiadParticipation.getParticipationResult())
                .build();
    }

    public static List<OlympiadParticipationDto> buildFromModels(List<OlympiadParticipation> olympiadParticipations) {
        return olympiadParticipations
                .stream()
                .map(OlympiadParticipationDto::buildFromModel)
                .collect(Collectors.toList());
    }
}
