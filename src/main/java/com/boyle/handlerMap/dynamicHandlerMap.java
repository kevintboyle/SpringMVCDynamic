package com.boyle.handlerMap;

/**
 * Created by kevinboyle on 2/28/16.
 */


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.LinkedHashMap;

public class dynamicHandlerMap extends SimpleUrlHandlerMapping implements InitializingBean {

    public void afterPropertiesSet() throws Exception {
        LinkedHashMap<String, Object> handlerMap = (LinkedHashMap) this.getUrlMap();
//        handlerMap.put("/foobar/{name}/{id}", "welcomeController");
        handlerMap.put("/foobar/{name}/{id}", getApplicationContext().getBean("welcomeController"));
        this.registerHandlers(handlerMap);
    }
}
