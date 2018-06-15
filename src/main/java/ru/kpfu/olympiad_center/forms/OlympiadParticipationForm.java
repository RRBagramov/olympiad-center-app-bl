package ru.kpfu.olympiad_center.forms;

import lombok.Data;

/**
 * 06.05.2018
 *
 * @author Robert Bagramov.
 */
@Data
public class OlympiadParticipationForm {
    private int olympiadId;
    private String participationResult;
    private int awardType;
}
