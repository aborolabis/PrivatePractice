package pl.sda.aborolabis.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PatientController {


    @RequestMapping(path = {"/patient"}, method = RequestMethod.GET)
    public String patient (Model model){
        return "patient/patient";
    }

}
