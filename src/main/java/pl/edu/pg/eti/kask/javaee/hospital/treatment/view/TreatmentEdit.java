package pl.edu.pg.eti.kask.javaee.hospital.treatment.view;

import lombok.Setter;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.TreatmentService;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Status;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Edit bean for single book.
 *
 * @author psysiu
 */
@Named
@ViewScoped
public class TreatmentEdit  implements Serializable {


    /**
     * Injected treatment service.
     */
    private TreatmentService service;

    /**
     * All doctors in storage.
     */
    private List<Doctor> availableDoctors;

    /**
     * Treatment to be displayed.
     */
    @Setter
    private Treatment treatment;

    public Treatment getTreatment() {
        if (treatment == null){
            treatment = new Treatment();
        }
        return treatment;
    }

    @Inject
    public TreatmentEdit(TreatmentService service) {
        this.service = service;
    }

    /**
     * @return all doctors in storage
     */
    public Collection<Doctor> getAvailableDoctors() {
            availableDoctors = service.findAllDoctors();
            return availableDoctors;
    }

    /**
     * @return all status types
     */
    public Collection<Status> getAvailableStatuses() {
        return List.of(Status.values());
    }

    /**
     * Saves currently viewed treatment in storage.
     *
     * @return navigation
     */
    public String saveTreatment() {
        service.saveTreatment(treatment);
        return "treatment_list?faces-redirect=true";
    }
}
