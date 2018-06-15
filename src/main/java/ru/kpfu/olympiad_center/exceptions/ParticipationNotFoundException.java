package ru.kpfu.olympiad_center.exceptions;

/**
 * 27.05.2018
 *
 * @author Robert Bagramov.
 */
public class ParticipationNotFoundException extends RuntimeException {
    public ParticipationNotFoundException(String message) {
        super(message);
    }
}
