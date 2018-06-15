package ru.kpfu.olympiad_center.views.resolvers;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import ru.kpfu.olympiad_center.views.ExcelView;

import java.util.Locale;

public class ExcelViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        return new ExcelView();
    }
}
