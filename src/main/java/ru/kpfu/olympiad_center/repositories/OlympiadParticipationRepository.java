package ru.kpfu.olympiad_center.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.olympiad_center.models.OlympiadParticipation;
import ru.kpfu.olympiad_center.models.Student;

import java.util.List;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
public interface OlympiadParticipationRepository extends CrudRepository<OlympiadParticipation, Integer> {
    List<OlympiadParticipation> findAllByStudent(Student student);
}
