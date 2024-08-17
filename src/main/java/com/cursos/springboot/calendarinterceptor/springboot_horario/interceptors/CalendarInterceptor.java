package com.cursos.springboot.calendarinterceptor.springboot_horario.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component("calendarInterceptor")
public class CalendarInterceptor implements HandlerInterceptor{

    @Value("${config.calendar.open}")
    private int open;

    @Value("${config.calendar.close}")
    private int close;
    

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);

                if (hour >= open && hour <= close){

                    StringBuilder message = new StringBuilder();
                    message.append("Hola! ");
                    message.append("Nuestro horario de atencion es de: ");
                    message.append(open);
                    message.append(" hrs. ");
                    message.append("a ");
                    message.append(close);
                    message.append(" hrs. ");
                    message.append("Gracias por su visita!");
                    request.setAttribute("message", message.toString());
                    return true;
                }
                StringBuilder message = new StringBuilder();
                message.append("Le recordamos que nuestro horario de atención es de: ");
                message.append(open);
                message.append(" hrs. ");
                message.append("a ");
                message.append(close);
                message.append(" hrs. ");
                message.append("Gracias por su visita!");

                
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> jsonMessage = new HashMap<>();
                jsonMessage.put("title","Atención al cliente se encuentra cerrado!");
                jsonMessage.put("message",message.toString());
                jsonMessage.put("date",new Date().toString());
                
                String json = objectMapper.writeValueAsString(jsonMessage);

                response.setContentType("application/json");
                response.setStatus(401);
                response.getWriter().write(json);

                return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
      
    }

    

    

}
