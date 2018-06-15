package ru.kpfu.olympiad_center.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kpfu.olympiad_center.models.Student;
import ru.kpfu.olympiad_center.views.ExcelView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 16.04.2018
 *
 * @author Robert Bagramov.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentDto extends AbstractUserDto {
    private String institute;
    private String faculty;
    private String educationForm;
    private String groupNumber;

    @Builder
    private StudentDto(int id,
                       String username,
                       String firstName,
                       String lastName,
                       String middleName,
                       String birthDate,
                       String sex,
                       String phone,
                       String email,
                       String institute,
                       String faculty,
                       String educationForm,
                       String groupNumber) {

        super(id, username, firstName, lastName, middleName, birthDate, sex, phone, email);
        this.institute = institute;
        this.faculty = faculty;
        this.educationForm = educationForm;
        this.groupNumber = groupNumber;
    }

    public static StudentDto buildFromModel(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .username(student.getUsername())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .middleName(student.getMiddleName())
                .birthDate(student.getBirthDate().format(DateTimeFormatter.ofPattern(ExcelView.DATE_PATTERN)))
                .groupNumber(student.getGroupNumber())
                .institute(student.getInstitute().getName())
                .faculty(student.getFaculty().getName())
                .educationForm(student.getEducationForm().name())
                .sex(student.getSex())
                .phone(student.getPhone())
                .email(student.getEmail())
                .build();
    }

    public static List<StudentDto> buildFromModels(List<Student> students) {
        return students
                .stream()
                .map(StudentDto::buildFromModel)
                .collect(Collectors.toList());
    }

}
