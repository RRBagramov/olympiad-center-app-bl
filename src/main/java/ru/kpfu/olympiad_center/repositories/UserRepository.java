package ru.kpfu.olympiad_center.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.olympiad_center.models.User;

import java.util.Optional;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findOneById(int id);

    Optional<User> findOneByUsername(String s);
}
