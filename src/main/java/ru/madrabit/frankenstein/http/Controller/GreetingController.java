package ru.madrabit.frankenstein.http.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.madrabit.frankenstein.dto.UserReadDTO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes("userReadDTO")
public class GreetingController {


//    @ModelAttribute("userReadDTO")
//    public UserReadDTO createUserReadDTO(@RequestParam("id") Long id, @RequestParam("username") String username) {
//        return new UserReadDTO(id, username);
//    }
    @GetMapping("/hello")
    public String hello(Model model,
                        HttpServletRequest request,
                        @ModelAttribute UserReadDTO userReadDTO) {
//        model.addAttribute("user", new UserReadDTO(1L, "Ivan"));
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

    @GetMapping("/buy")
    public String buy(Model model, @SessionAttribute("userReadDTO") UserReadDTO userReadDTO) {
        return "greeting/buy";
    }
}
