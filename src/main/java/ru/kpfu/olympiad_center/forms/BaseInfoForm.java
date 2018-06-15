package ru.kpfu.olympiad_center.forms;

import lombok.Data;

/**
 * 03.05.2018
 *
 * @author Robert Bagramov.
 */
@Data//TODO:добавить username
public class BaseInfoForm {
    private int userId;

    private String firstName = "";

    private String lastName = "";

    private String middleName = "";

    private String birthDate = "";

    private String sex = "";
}
