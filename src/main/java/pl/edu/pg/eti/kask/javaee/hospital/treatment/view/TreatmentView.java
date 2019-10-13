package pl.edu.pg.eti.kask.javaee.hospital.treatment.view;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * View bean for single treatment.
 *
 * @author etusien
 */
@Named
@RequestScoped
public class TreatmentView {

    /**
     * Treatment to be displayed.
     */
    @Getter
    @Setter
    private Treatment treatment;
}
