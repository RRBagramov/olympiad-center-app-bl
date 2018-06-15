package ru.kpfu.olympiad_center.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.olympiad_center.models.Student;

import java.util.Optional;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findOneByEmail(String email);

    Optional<Student> findOneByUsername(String username);

    Optional<Student> findOneById(int id);
}
