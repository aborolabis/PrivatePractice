package pl.sda.aborolabis.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorController {

    @RequestMapping(path = { "/doctor" }, method = RequestMethod.GET)
    public String doctorLog(Model model) {
        return "/doctor/doctor";
    }

}
