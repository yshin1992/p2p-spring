package org.background.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req,  Exception e) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorMsg", e.toString());
		e.printStackTrace();
		return mv;
	}
	
}
