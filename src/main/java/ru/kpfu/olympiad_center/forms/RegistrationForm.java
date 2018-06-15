package ru.kpfu.olympiad_center.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 19.04.2018
 *
 * @author Robert Bagramov.
 */
@Data
public class RegistrationForm {
    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";

    @NotEmpty
    private String middleName = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String repassword = "";

    @NotEmpty
    private String username;
}
