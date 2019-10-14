package pl.edu.pg.eti.kask.javaee.hospital.doctor.view;

import lombok.Setter;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.DoctorService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.MedicalSpecialization;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Edit bean for single doctor.
 *
 * @author ewatusien
 */
@Named
@ViewScoped
public class DoctorEdit implements Serializable {

    /**
     * Injected doctor service.
     */
    @Inject
    private DoctorService service;

    /**
     * Doctor to be displayed.
     */
    @Setter
    private Doctor doctor ;

    public Doctor getDoctor() {
        if (doctor == null){
            doctor = new Doctor();
        }
        return doctor;
    }

    /**
     * Saves currently viewed doctor in storage.
     *
     * @return navigation
     */
    public String saveDoctor() {
        service.saveDoctor(doctor);
        return "doctor_list?faces-redirect=true";
    }

    public Collection<MedicalSpecialization> getAvailableSpecializations() {
        return List.of(MedicalSpecialization.values());
    }


}
