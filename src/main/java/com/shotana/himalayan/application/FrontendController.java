package com.shotana.himalayan.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontendController {

    @RequestMapping("/")
    public String index(ModelAndView mav) {
        mav.addObject("message", "this is the text from Spring");
        return "index";
    }
}
