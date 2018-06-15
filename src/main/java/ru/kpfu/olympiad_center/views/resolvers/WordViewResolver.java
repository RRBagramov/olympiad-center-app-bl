package ru.kpfu.olympiad_center.views.resolvers;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import ru.kpfu.olympiad_center.views.WordView;

import java.util.Locale;

/**
 * 20.05.2018
 *
 * @author Robert Bagramov.
 */
public class WordViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return new WordView();
    }
}
