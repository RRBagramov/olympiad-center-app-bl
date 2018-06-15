package ru.kpfu.olympiad_center.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.olympiad_center.dto.StudentDto;
import ru.kpfu.olympiad_center.dto.UserDto;
import ru.kpfu.olympiad_center.exceptions.IllegalUserAccessException;
import ru.kpfu.olympiad_center.exceptions.IncorrectRequest;
import ru.kpfu.olympiad_center.forms.*;
import ru.kpfu.olympiad_center.models.*;
import ru.kpfu.olympiad_center.models.enums.EducationForm;
import ru.kpfu.olympiad_center.repositories.OlympiadParticipationRepository;
import ru.kpfu.olympiad_center.repositories.StudentRepository;
import ru.kpfu.olympiad_center.repositories.UserRepository;
import ru.kpfu.olympiad_center.security.details.CurrentUser;
import ru.kpfu.olympiad_center.services.UserService;
import ru.kpfu.olympiad_center.views.ExcelView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * 02.05.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final OlympiadParticipationRepository olympiadParticipationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, StudentRepository studentRepository,
                           OlympiadParticipationRepository olympiadParticipationRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.olympiadParticipationRepository = olympiadParticipationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserByAuth(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
        return UserDto.buildFromModel(optionalUser.get());
    }

    @Override
    public StudentDto getStudentByAuth(Authentication authentication) {
        CurrentUser userDetails = (CurrentUser) authentication.getPrincipal();
        Optional<Student> optionalUser = studentRepository.findOneByUsername(userDetails.getUsername());

        return optionalUser
                .map(StudentDto::buildFromModel)
                .orElseThrow(() -> new UsernameNotFoundException("user with " + userDetails.getUsername() + " not found"));
    }

    @Override
    public void submitUserProfileContacts(Authentication authentication, ContactsForm contactsForm) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUserFromAuth = userRepository.findOneByUsername(userDetails.getUsername());
        Optional<User> optionalUserFromForm = userRepository.findOneById(contactsForm.getUserId());

        if (equalsOptionalUserById(optionalUserFromAuth, optionalUserFromForm)) {
            User user = updateUserProfileContacts(optionalUserFromAuth.get(), contactsForm);
            userRepository.save(user);
        } else {
            throw new IncorrectRequest("Not user's profile");
        }
    }

    @Override
    public void submitUserProfileBase(Authentication authentication, BaseInfoForm baseInfoForm) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUserFromAuth = userRepository.findOneByUsername(userDetails.getUsername());
        Optional<User> optionalUserFromForm = userRepository.findOneById(baseInfoForm.getUserId());

        if (equalsOptionalUserById(optionalUserFromAuth, optionalUserFromForm)) {
            User user = updateUserProfileBase(optionalUserFromAuth.get(), baseInfoForm);
            userRepository.save(user);
        } else {
            throw new IncorrectRequest("Not user's profile");
        }
    }

    @Override
    public void submitStudentProfileEducation(Authentication authentication, EducationInfoForm educationInfoForm) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<Student> optionalUserFromAuth = studentRepository.findOneByUsername(userDetails.getUsername());
        Optional<Student> optionalUserFromForm = studentRepository.findOneById(educationInfoForm.getUserId());

        if (equalsOptionalUserById(optionalUserFromAuth, optionalUserFromForm)) {
            Student student = updateStudentProfileEducation(optionalUserFromAuth.get(), educationInfoForm);
            userRepository.save(student);
        } else {
            throw new IncorrectRequest("Not user's profile");
        }
    }

    @Override
    public void submitStudentOlympiadParticipation(Authentication authentication, OlympiadParticipationForm olympiadParticipationForm) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<Student> optionalUser = studentRepository.findOneByUsername(userDetails.getUsername());

        OlympiadParticipation olympiadParticipation = OlympiadParticipation.builder()
                .olympiad(new Olympiad(olympiadParticipationForm.getOlympiadId()))
                .participationResult(olympiadParticipationForm.getParticipationResult())
                .student(optionalUser.get())
                .build();

        olympiadParticipationRepository.save(olympiadParticipation);
    }

    @Override
    public void deleteStudentOlympiadParticipationById(Authentication authentication, int olympiadParticipationId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<Student> optionalUser = studentRepository.findOneByUsername(userDetails.getUsername());
        Optional<OlympiadParticipation> optionalOlympiadParticipation = olympiadParticipationRepository
                .findById(olympiadParticipationId);

        if (optionalOlympiadParticipation.isPresent()) {
            Student authStudent = optionalUser.get();
            Student studentOlympiadParticipation = optionalOlympiadParticipation.get().getStudent();

            if (authStudent.getId() == studentOlympiadParticipation.getId()) {
                olympiadParticipationRepository.deleteById(olympiadParticipationId);
            } else {
                throw new IllegalUserAccessException("Illegal required permissions");
            }
        } else {
            throw new IllegalArgumentException("Olympiad participation ID not found");
        }
    }

    @Override
    public void submitUserProfilePassword(Authentication authentication, PasswordForm passwordForm) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());

        String newHashPassword = passwordEncoder.encode(passwordForm.getNewPassword());
        optionalUser.ifPresent(user ->
        {
            user.setPassword(newHashPassword);
            userRepository.save(user);
        });

    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return StudentDto.buildFromModels(students);
    }

    private Student updateStudentProfileEducation(Student student, EducationInfoForm educationInfoForm) {
        student.setInstitute(new Institute(educationInfoForm.getInstitute()));
        student.setFaculty(new Faculty(educationInfoForm.getFaculty()));
        student.setEducationForm(EducationForm.valueOf(educationInfoForm.getEducationForm()));
        student.setGroupNumber(educationInfoForm.getGroupNumber());

        return student;
    }

    private User updateUserProfileContacts(User user, ContactsForm contactsForm) {
        user.setEmail(contactsForm.getEmail());
        user.setPhone(contactsForm.getPhone());

        return user;
    }

    private User updateUserProfileBase(User user, BaseInfoForm baseInfoForm) {
        user.setFirstName(baseInfoForm.getFirstName());
        user.setLastName(baseInfoForm.getLastName());
        user.setMiddleName(baseInfoForm.getMiddleName());
        user.setBirthDate(LocalDate.parse(baseInfoForm.getBirthDate(), DateTimeFormatter.ofPattern(ExcelView.DATE_PATTERN)));
        user.setSex(baseInfoForm.getSex());

        return user;
    }

    private boolean equalsOptionalUserById(Optional<? extends User> firstUser, Optional<? extends User> secondUser) {
        if (firstUser.isPresent() && secondUser.isPresent()) {
            return firstUser.get().getId() == secondUser.get().getId();
        }

        return false;
    }

}
