package pl.sda.aborolabis.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.aborolabis.project.form.PatientHistoryForm;
import pl.sda.aborolabis.project.form.VisitForm;
import pl.sda.aborolabis.project.model.Hours;
import pl.sda.aborolabis.project.service.CalendarService;
import pl.sda.aborolabis.project.service.DoctorServiceImpl;
import pl.sda.aborolabis.project.service.PatientServiceImpl;
import pl.sda.aborolabis.project.service.VisitServiceImpl;

@Controller
public class DoctorController {

    private final PatientServiceImpl patientService;
    private final DoctorServiceImpl doctorService;
    private final CalendarService calendarService;
    private final VisitServiceImpl visitService;

    public DoctorController(PatientServiceImpl patientService, DoctorServiceImpl doctorService, CalendarService calendarService, VisitServiceImpl visitService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.calendarService = calendarService;
        this.visitService = visitService;
    }

    @RequestMapping(path = { "/doctor" }, method = RequestMethod.GET)
    public String doctorLog(Model model) {
        return "doctor/doctor";
    }

    @RequestMapping(path = {"/doctor/logged"}, method = RequestMethod.GET)
    public String doctor(Model model){
        return "doctor2";
    }

    @RequestMapping (path = {"doctor/patients"}, method = RequestMethod.GET)
    public String patients(Model model){
        model.addAttribute("patients", patientService.findAll());
        return "doctor/doctorPatients";
    }

    @RequestMapping(path = {"doctor/visits"}, method = RequestMethod.GET)
    public String allVisits(Model model){
        model.addAttribute("visits", doctorService.getAllVisitsForCurrentDoctor());
        return "doctor/doctorVisits";
    }

    @RequestMapping (path = {"doctor/visits/add"}, method = RequestMethod.GET)
    public String visits(Model model){
        model.addAttribute("visitForm", new VisitForm());
        model.addAttribute("hours", Hours.getAllHours());
        return "doctor/doctorAddVisit";
    }

    @RequestMapping(path = {"doctor/visits/add"}, method = RequestMethod.POST)
    public String addAVisit(@ModelAttribute("visitForm") VisitForm visitForm,
                            Model model) {
        doctorService.addVisit(visitForm);
        return "redirect:/doctor/visits";
    }

    @RequestMapping (path = {"doctor/current"}, method = RequestMethod.GET)
    public String currentVisit(Model model){
        model.addAttribute("todaysVisits", calendarService.getTodaysVisits());
        return "doctor/doctorCurrent";
    }

    @RequestMapping(path = {"doctor/current/edit"}, method = RequestMethod.GET)
    public String editCurrentVisit(Model model, @RequestParam("id") Long visitID){
        model.addAttribute("visit", visitService.getVisitById(visitID));
        model.addAttribute("patientHistoryForm", new PatientHistoryForm());
        model.addAttribute("isVisitCompl", visitService.isVisitCompleted(visitService.getVisitById(visitID)));
        return "doctor/doctorEditCurrent";
    }

    @RequestMapping(path = {"doctor/current/edit"}, method = RequestMethod.POST)
    public String editCurrentVisit(@ModelAttribute("patientHistoryForm") PatientHistoryForm patientHistoryForm,
                                   Model model){
        visitService.updateVisit(patientHistoryForm);
        return "redirect:/doctor/current";
    }
}
