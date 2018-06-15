package ru.kpfu.olympiad_center.models;

import lombok.*;
import ru.kpfu.olympiad_center.models.enums.OlympiadLevel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "olympiad")
@Entity
@Builder
public class Olympiad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String organizer;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @Column
    @Enumerated
    private OlympiadLevel level;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column
    private boolean deleted;

    @OneToMany(mappedBy = "olympiad")
    private List<OlympiadParticipation> olympiadParticipation;

    public Olympiad(int id) {
        this.id = id;
    }
}
