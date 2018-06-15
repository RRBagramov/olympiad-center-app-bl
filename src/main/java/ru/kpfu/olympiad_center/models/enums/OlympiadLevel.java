package ru.kpfu.olympiad_center.models.enums;

/**
 * 29.04.2018
 *
 * @author Robert Bagramov.
 */
public enum OlympiadLevel {
    INTERNATIONAL,
    ALLRUSSIAN,
    REGIONAL,
    INTERUNIVERSITY,
    UNIVERSITY,
    INSTITUTE,
    OTHER;

    public String rusName() {
        switch (this) {
            case INSTITUTE:
                return "Институтский";
            case ALLRUSSIAN:
                return "Всероссийский";
            case REGIONAL:
                return "Региональный";
            case UNIVERSITY:
                return "Вузовский";
            case INTERNATIONAL:
                return "Международный";
            case INTERUNIVERSITY:
                return "Внутриуниверситетский";
            default:
                return "Другой";
        }
    }
}
