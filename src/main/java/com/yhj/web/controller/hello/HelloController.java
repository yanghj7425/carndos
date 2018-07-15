package com.yhj.web.controller.hello;

import com.yhj.web.controller.common.BaseController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController extends BaseController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        System.out.println("index");

        return "login";
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(ModelMap model) {

        model.addAttribute("userName", getPrincipal());

        return "hello";
    }


    @GetMapping(value = "denied")
    public String errorPage() {

        logger.debug("message : {}", "error");
        return "denied";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("logout");
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = {"/hello"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello");
        model.addObject("message", "This is welcome page");
        model.addObject("userName", getPrincipal());
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


    @RequestMapping(value = "/dba**", method = RequestMethod.GET)
    public ModelAndView dbaPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Hello dba");
        model.addObject("message", "this is dba page");
        model.setViewName("dba");
        return model;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout)

    {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "用户名或密码错误");
        }
        if (logout != null) {
            model.addObject("logout", "成功退出");
        }
        model.setViewName("login");
        return model;
    }


    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


}
