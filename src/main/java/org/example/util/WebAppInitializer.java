package org.example.util;

import org.example.config.MvcConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; // Можно вернуть `null`, если нет глобальной конфигурации Spring (например, базы данных)
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfiguration.class}; // Указываем класс конфигурации Spring MVC
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // Все запросы идут через DispatcherServlet
    }
}
