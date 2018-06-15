package ru.kpfu.olympiad_center.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.olympiad_center.dto.StudentDto;
import ru.kpfu.olympiad_center.dto.UserDto;
import ru.kpfu.olympiad_center.forms.*;

import java.util.List;


/**
 * 18.04.2018
 *
 * @author Robert Bagramov.
 */
public interface UserService {
    UserDto getUserByAuth(Authentication authentication);

    StudentDto getStudentByAuth(Authentication authentication);

    List<StudentDto> getAllStudents();

    void submitUserProfileContacts(Authentication authentication, ContactsForm contactsForm);

    void submitUserProfileBase(Authentication authentication, BaseInfoForm baseInfoForm);

    void submitStudentProfileEducation(Authentication authentication, EducationInfoForm educationInfoForm);

    void submitStudentOlympiadParticipation(Authentication authentication,
                                            OlympiadParticipationForm olympiadParticipationForm);

    void deleteStudentOlympiadParticipationById(Authentication authentication, int olympiadParticipationId);

    void submitUserProfilePassword(Authentication authentication, PasswordForm passwordForm);
}
