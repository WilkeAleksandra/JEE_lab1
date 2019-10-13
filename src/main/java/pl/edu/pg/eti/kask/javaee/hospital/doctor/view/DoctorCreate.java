package pl.edu.pg.eti.kask.javaee.hospital.doctor.view;

import pl.edu.pg.eti.kask.javaee.hospital.doctor.DoctorService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Create bean for single doctor.
 *
 * @author ewatusien
 */
@Named
@ViewScoped
public class DoctorCreate extends DoctorEdit {

    @Inject
    public DoctorCreate(DoctorService service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        setDoctor(new Doctor());
    }
}
