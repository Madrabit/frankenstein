package ru.madrabit.frankenstein.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.madrabit.frankenstein.dto.UserReadDTO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes("user")
public class GreetingController {


//    @ModelAttribute("userReadDTO")
//    public UserReadDTO createUserReadDTO(@RequestParam("id") Long id, @RequestParam("username") String username) {
//        return new UserReadDTO(id, username);
//    }
@GetMapping("/hello")
public String hello(Model model,
                    HttpServletRequest request,
                    @ModelAttribute("userReadDto") UserReadDTO userReadDto) {

    model.addAttribute("user", userReadDto);

    return "greeting/hello";
}

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView, HttpServletRequest request,
                               @RequestParam Integer age,
                               @RequestHeader String accept,
                               @CookieValue("JSESSIONID") String JSESSIONID,
                               @PathVariable("id") Integer id) {
        String parameterAge = request.getParameter("age");
        String headerAccept = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserReadDTO user, Model model) {
        return "greeting/bye";
    }
}
