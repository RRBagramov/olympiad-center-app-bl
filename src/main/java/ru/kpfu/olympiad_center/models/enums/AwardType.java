package ru.kpfu.olympiad_center.models.enums;

/**
 * 29.05.2018
 *
 * @author Robert Bagramov.
 */
public enum AwardType {
    PRIZE, GRANT, HONORY_DIPLOMA, MEDAL, DIPLOMA,
    THANKS_LETTER, MORE;

    public String rusName() {
        switch (this) {
            case GRANT:
                return "стипендия";
            case MEDAL:
                return "медаль";
            case PRIZE:
                return "премия";
            case DIPLOMA:
                return "диплом";
            case THANKS_LETTER:
                return "благодарственное письмо";
            case HONORY_DIPLOMA:
                return "почетная грамота";
            default:
                return "другое";
        }
    }
}
