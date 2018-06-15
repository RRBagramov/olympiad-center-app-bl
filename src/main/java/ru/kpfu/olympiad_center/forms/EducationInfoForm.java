package ru.kpfu.olympiad_center.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 03.05.2018
 *
 * @author Robert Bagramov.
 */
@Data
public class EducationInfoForm {
    @NotEmpty
    private int userId;

    @NotEmpty
    private String institute;

    @NotEmpty
    private String faculty;

    @NotEmpty
    private String educationForm;

    @NotEmpty
    private String groupNumber;

}
