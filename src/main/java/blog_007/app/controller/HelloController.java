package blog_007.app.controller;

import blog_007.app.model.User;
import blog_007.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HelloController {


    private static final Logger log = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }


    @GetMapping("user/login")
    public ModelAndView loginpage() {
        return new ModelAndView("user/login");
    }

    @GetMapping("/user/home")
    public String homePage() {
        return "user/home";
    }


    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("signup");
        return model;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");

        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error.user", "Password didnt match!!!!");
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("signup");
        }

        return model;



    }


    }
