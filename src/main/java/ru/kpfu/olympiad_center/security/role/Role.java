package ru.kpfu.olympiad_center.security.role;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
public enum Role {
    ADMIN, TEACHER, STUDENT;

    public String rusName() {
        if (this.equals(Role.TEACHER)) {
            return "сотрудник";
        } else if (this.equals(Role.STUDENT)) {
            return "студент";
        } else {
            return "админ";
        }
    }
}
