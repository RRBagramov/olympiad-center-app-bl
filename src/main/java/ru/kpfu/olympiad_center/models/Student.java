package ru.kpfu.olympiad_center.models;

import lombok.*;
import ru.kpfu.olympiad_center.models.enums.EducationForm;

import javax.persistence.*;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "student_details")
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    private Institute institute;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @Column(name = "education_form")
    private EducationForm educationForm;

    @Column(name = "group_number")
    private String groupNumber;
}
