package ru.kpfu.olympiad_center.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 03.05.2018
 *
 * @author Robert Bagramov.
 */
@Data
public class ContactsForm {
    @NotEmpty
    private int userId;

    @NotEmpty
    private String phone;

    @Email
    private String email;
}
