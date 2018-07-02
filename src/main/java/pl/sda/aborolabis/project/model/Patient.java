package pl.sda.aborolabis.project.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient extends User {

    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;

    private String pesel;

    private boolean insurance;

    public Patient() {
    }

    public Patient(String username, String password, String firstName, String lastName, Person person, LocalDate dateOfBirth, Address address, String pesel, boolean insurance) {
        super(username, password, firstName, lastName, person);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.pesel = pesel;
        this.insurance = insurance;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }
}
