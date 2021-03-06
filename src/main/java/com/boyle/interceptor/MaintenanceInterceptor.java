package com.boyle.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MaintenanceInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware {

    private int maintenanceStartTime;
    private int maintenanceEndTime;
    private String maintenanceMapping;

    public void setMaintenanceMapping(String maintenanceMapping) {
        this.maintenanceMapping = maintenanceMapping;
    }

    public void setMaintenanceStartTime(int maintenanceStartTime) {
        this.maintenanceStartTime = maintenanceStartTime;
    }

    public void setMaintenanceEndTime(int maintenanceEndTime) {
        this.maintenanceEndTime = maintenanceEndTime;
    }

    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(cal.HOUR_OF_DAY);

        if (hour >= maintenanceStartTime && hour <= maintenanceEndTime) {
            //maintenance time, send to maintenance page
            response.sendRedirect(maintenanceMapping);
            return false;
        } else {
            return true;
        }

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        int i = 0 ;
    }
}