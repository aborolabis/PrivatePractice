package pl.sda.aborolabis.project.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.sda.aborolabis.project.repository.DoctorRepository;
import pl.sda.aborolabis.project.repository.PatientRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessLoginHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public SuccessLoginHandler(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response,
                          Authentication authentication) throws IOException, ServletException
    {

        Object principal = authentication.getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        if(isUserDoctor(username)){
            new DefaultRedirectStrategy().sendRedirect(request, response, "/doctor");
        } else if(isUserPatient(username)){
            new DefaultRedirectStrategy().sendRedirect(request, response, "/patient");
        } else {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/main");
        }
    }

    private boolean isUserDoctor(String username){
        return doctorRepository.findByUsername(username).isPresent();
    }

    private boolean isUserPatient(String username){
        return patientRepository.findByUsername(username).isPresent();
    }
}