package ru.kpfu.olympiad_center.dto;

import lombok.Builder;
import ru.kpfu.olympiad_center.models.User;
import ru.kpfu.olympiad_center.views.ExcelView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 16.04.2018
 *
 * @author Robert Bagramov.
 */
public class UserDto extends AbstractUserDto {
    @Builder
    public UserDto(int id,
                   String username,
                   String firstName,
                   String lastName,
                   String middleName,
                   String birthDate,
                   String sex,
                   String phone,
                   String email) {
        super(id, username, firstName, lastName, middleName, birthDate, sex, phone, email);
    }

    public static UserDto buildFromModel(User user) {
        String formatBirthDate = "";
        LocalDate birthDate = user.getBirthDate();
        if (birthDate != null) {
            formatBirthDate = birthDate.format(DateTimeFormatter.ofPattern(ExcelView.DATE_PATTERN));
        }

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .birthDate(formatBirthDate)
                .sex(user.getSex())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }


}
