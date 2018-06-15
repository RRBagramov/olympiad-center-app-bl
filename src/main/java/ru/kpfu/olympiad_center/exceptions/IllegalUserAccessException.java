package ru.kpfu.olympiad_center.exceptions;

/**
 * 07.05.2018
 *
 * @author Robert Bagramov.
 */
public class IllegalUserAccessException extends RuntimeException {
    public IllegalUserAccessException(String message) {
        super(message);
    }
}
