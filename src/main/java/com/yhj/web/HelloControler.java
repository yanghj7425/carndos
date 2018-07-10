package com.yhj.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloControler {


    @RequestMapping(value = {"/", "welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello");
        model.addObject("message", "This is welcome page");

        model.setViewName("hello");

        return model;
    }


    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();

        model.addObject("title", "Spring Security Hello World");

        model.addObject("message", "this is protected page");

        model.setViewName("admin");

        return model;

    }


}
