package pl.edu.pg.eti.kask.javaee.hospital.treatment.view;

import pl.edu.pg.eti.kask.javaee.hospital.HospitalService;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Create bean for single treatment.
 *
 * @author ewatusien
 */
@Named
@ViewScoped
public class TreatmentCreate extends TreatmentEdit {

    @Inject
    public TreatmentCreate(HospitalService service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        setTreatment(new Treatment());
    }
}
