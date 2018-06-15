package ru.kpfu.olympiad_center.forms;

import lombok.Data;

/**
 * 09.05.2018
 *
 * @author Robert Bagramov.
 */
@Data
public class PasswordForm {
    private String oldPassword;
    private String newPassword;
    private String reNewPassword;
}
