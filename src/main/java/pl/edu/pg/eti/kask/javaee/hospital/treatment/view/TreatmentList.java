package pl.edu.pg.eti.kask.javaee.hospital.treatment.view;

import pl.edu.pg.eti.kask.javaee.hospital.HospitalService;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * View bean for treatment list.
 *
 * @author ewatusien
 */
@Named
@RequestScoped
public class TreatmentList {

    /**
     * Injected treatment service.
     */
    private HospitalService service;

    /**
     * Lazy loaded list of treatments.
     */
    private List<Treatment> treatments;

    @Inject
    public TreatmentList(HospitalService service) {
        this.service = service;
    }

    /**
     * @return all books in storage
     */
    public List<Treatment> getTreatments() {
        if (treatments == null) {
            treatments = service.getTreatmentService().findAllTreatments();
        }
        return treatments;
    }

    /**
     * Deletes treatment from the storage.
     *
     * @param treatment treatment to be deleted
     * @return navigation url
     */
    public String removeTreatment(Treatment treatment) {
        service.getTreatmentService().removeTreatment(treatment);
        return "treatment_list?faces-redirect=true";
    }
}
