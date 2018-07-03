package pl.sda.aborolabis.project.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.aborolabis.project.exception.UserNotFoundException;
import pl.sda.aborolabis.project.repository.DoctorRepository;
import pl.sda.aborolabis.project.repository.PatientRepository;
import pl.sda.aborolabis.project.repository.UserRepository;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public UserService(UserRepository userRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        pl.sda.aborolabis.project.model.User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());

        return toUser(foundUser);
    }

    private UserDetails toUser(pl.sda.aborolabis.project.model.User user) {
        if(doctorRepository.findByUsername(user.getUsername()).isPresent()){
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities("DOCTOR", "NURSE")
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .build();
        } else if(patientRepository.findByUsername(user.getUsername()).isPresent()){
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities("PATIENT")
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .build();
        } else {
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities("NURSE", "DOCTOR", "PATIENT")
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .build();
        }


    }
}
