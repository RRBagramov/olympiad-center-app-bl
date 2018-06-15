package ru.kpfu.olympiad_center.models;

import lombok.*;
import ru.kpfu.olympiad_center.models.enums.UserState;
import ru.kpfu.olympiad_center.security.role.Role;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "olympiad_center_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected int id;

    @Column(unique = true)
    protected String username;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "middle_name")
    protected String middleName;

    @Column(name = "birth_date")
    protected LocalDate birthDate;

    @Column
    protected String password;

    @Column
    protected String sex;

    @Column(unique = true)
    protected String phone;

    @Column(unique = true)
    protected String email;

    @Column(name = "arrival_date")
    protected Timestamp arrivalDate;

    @Column
    @Enumerated(EnumType.STRING)
    protected Role role;

    @Column
    @Enumerated(EnumType.STRING)
    protected UserState state;

    @OneToMany(mappedBy = "student")
    private List<OlympiadParticipation> olympiadParticipation;
}

