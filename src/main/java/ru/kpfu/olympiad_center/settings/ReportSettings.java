package ru.kpfu.olympiad_center.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 26.05.2018
 *
 * @author Robert Bagramov.
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "olymp.report")
public final class ReportSettings {
    public static final int APPLICATION_RATIONAL_REPORT = 1;
    public static final int PARTICIPATION_REPORT = 2;
    public static final int RECOMMENDATION_LETTER = 3;

    private String applicationRationalePath;
    private String participationPath;
    private String recommendationLetterPath;
}
