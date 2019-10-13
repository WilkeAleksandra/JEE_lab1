package pl.edu.pg.eti.kask.javaee.hospital.doctor.view;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * View bean for single doctor.
 *
 * @author ewatusien
 */

@Named
@RequestScoped
public class DoctorView {

    /**
     * Book to be displayed.
     */
    @Setter
    @Getter
    private Doctor doctor;
}
