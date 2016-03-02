package com.boyle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Map;

/**
 * Created by kevinboyle on 2/29/16.
 */
public class genericController<Controller extends ControllerInterface, MetaData> extends AbstractController {

    private String readAll ( BufferedReader reader ) {
        if ( reader == null )
            return "";
        StringBuilder b = new StringBuilder();

        String line ="";
        try {
            while ((line = reader.readLine()) != null) {
                b.append(line);
            }

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return b.toString();
    }

    //http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.htmlwww

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {String url = request.getRequestURI();
        String method = request.getMethod();


        String format = "/foobar/{name}/{id}";
        AntPathMatcher pathMatcher = new AntPathMatcher();
        Map<String, String> variables = pathMatcher.extractUriTemplateVariables(format, url);
        String post_data = null;

        ModelAndView model = new ModelAndView("WelcomePage");
        if ( method != null && method.equals("POST")) {
            BufferedReader reader = request.getReader();
            post_data = readAll( reader );
        }

        model.addObject("classname", "WelcomeController - "+variables.get("id"));
        model.addObject("executeTime",65L);

        return model;
    }

    private void schema_validator ()
    {
        ObjectMapper m = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        JsonSchema jsonSchema = visitor.finalSchema();

    }
}
