package com.codegym.configuration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityWebAppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[] { characterEncodingFilter};
    }

    /*
    Tầng giao vận TCP/IP không quan tâm với bảng mã, nó đơn giản và vận chuyển gói tin,
     từng byte một. Phần lớn web server, trừ khi là web server do bạn tự viết,
     decode các bytes này để có các parametter theo lối như thể rằng các bytes đó
      trước kia là ký tự của bảng mã ISO-8859-1. Nếu bạn nhìn các ký tự đã được
      decode ra dưới con mắt của bảng mã UTF-8 (trớ trêu rằng, phần lớn các ngôn ngữ lập trình làm như thế),
       bạn sẽ nhận được kết quả không mong muốn.
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic filterRegistration =
                servletContext.addFilter("endcoding-filter", new CharacterEncodingFilter());
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");

        //make sure encodingFilter is matched most first, by "false" arg
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

        super.onStartup(servletContext);
    }
}
