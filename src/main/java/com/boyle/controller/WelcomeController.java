package com.boyle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.util.*;
import java.util.*;
import java.io.BufferedReader;
import com.fasterxml.jackson.module.jsonSchema.*;
import com.fasterxml.jackson.module.jsonSchema.factories.*;
import com.fasterxml.jackson.databind.*;


public class WelcomeController extends AbstractController{

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