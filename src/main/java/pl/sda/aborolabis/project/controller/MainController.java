package pl.sda.aborolabis.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public String index (Model model){
        return "index";
    }

    @RequestMapping(path = {"/login"}, method = RequestMethod.GET)
    public String login (Model model){
        return "login";
    }
}
