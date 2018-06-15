package ru.kpfu.olympiad_center.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.olympiad_center.models.Olympiad;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
public interface OlympiadRepository extends CrudRepository<Olympiad, Integer> {
    Optional<Olympiad> findById(Integer olympiadId);

    Optional<List<Olympiad>> findAllByBeginDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate beginDate, LocalDate endDate);
}
