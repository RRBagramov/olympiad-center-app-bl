package ru.kpfu.olympiad_center.models;

import lombok.*;
import ru.kpfu.olympiad_center.models.enums.AwardType;

import javax.persistence.*;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "olympiad_participation")
@Entity
@Builder
public class OlympiadParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "olympiad_id", referencedColumnName = "id")
    private Olympiad olympiad;

    @Column(name = "participation_result")
    private String participationResult;

    @Column(name = "award_type")
    @Enumerated(EnumType.STRING)
    private AwardType awardType;
}
