package ru.kpfu.olympiad_center.services;

import org.springframework.security.core.Authentication;

/**
 * 01.05.2018
 *
 * @author Robert Bagramov.
 */
public interface AuthService {
    String getCurrentUserRole(Authentication authentication);
}
