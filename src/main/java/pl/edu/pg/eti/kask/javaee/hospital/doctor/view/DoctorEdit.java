package pl.edu.pg.eti.kask.javaee.hospital.doctor.view;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.DoctorService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

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
    private DoctorService service;

    /**
     * Doctor to be displayed.
     */
    @Setter
    @Getter
    private Doctor doctor;

    @Inject
    public DoctorEdit(DoctorService service) {
        this.service = service;
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

}
