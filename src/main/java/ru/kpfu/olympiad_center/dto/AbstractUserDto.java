package ru.kpfu.olympiad_center.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 06.05.2018
 *
 * @author Robert Bagramov.
 */
@Data
@AllArgsConstructor
public abstract class AbstractUserDto {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String sex;
    private String phone;
    private String email;
}
