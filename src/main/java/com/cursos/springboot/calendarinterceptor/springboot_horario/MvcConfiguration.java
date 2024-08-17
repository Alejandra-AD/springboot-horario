package com.cursos.springboot.calendarinterceptor.springboot_horario;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class MvcConfiguration implements WebMvcConfigurer{


    private HandlerInterceptor calendarInterceptor;

    public MvcConfiguration(@Qualifier("calendarInterceptor")HandlerInterceptor calendarInterceptor) {
        this.calendarInterceptor = calendarInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(calendarInterceptor).addPathPatterns("/app/foo");
    
    }


    



}
