package ru.kpfu.olympiad_center.exceptions;

/**
 * 03.05.2018
 *
 * @author Robert Bagramov.
 */
public class IncorrectRequest extends RuntimeException {
    public IncorrectRequest(String message) {
        super(message);
    }
}
