package ru.kpfu.olympiad_center.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.kpfu.olympiad_center.views.resolvers.WordViewResolver;
import ru.kpfu.olympiad_center.views.resolvers.ExcelViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 19.05.2018
 *
 * @author Robert Bagramov.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers = new ArrayList<>();

        resolvers.add(freeMarkerViewResolver());
        resolvers.add(docViewResolver());
        resolvers.add(xlsViewResolver());

        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    @Bean(name = "viewResolver")
    public ViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();

        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setCache(true);
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".ftl");
        viewResolver.setOrder(0);

        return viewResolver;
    }

    @Bean(name = "freemarkerConfig")
    public FreeMarkerConfigurer getFreemarkerConfig() {
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();

        config.setTemplateLoaderPath("classpath:/templates");
        return config;
    }

    @Bean
    public ViewResolver docViewResolver() {
        return new WordViewResolver();
    }

    @Bean
    public ViewResolver xlsViewResolver() {
        return new ExcelViewResolver();
    }

}
