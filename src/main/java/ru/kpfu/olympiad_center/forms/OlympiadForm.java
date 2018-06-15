package ru.kpfu.olympiad_center.forms;

import lombok.Data;
import ru.kpfu.olympiad_center.models.City;
import ru.kpfu.olympiad_center.models.Country;

import java.sql.Date;

/**
 * 04.05.2018
 *
 * @author Robert Bagramov.
 */
@Data
public class OlympiadForm {
    private String name;

    private String organizer;

    private Country country;

    private City city;

    private String level;

    private String beginDate;

    private String endDate;

}
