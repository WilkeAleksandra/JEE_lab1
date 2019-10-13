package pl.edu.pg.eti.kask.javaee.hospital.treatment.view.converter;

import pl.edu.pg.eti.kask.javaee.hospital.treatment.TreatmentService;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Treatment.class, managed = true)
@Dependent
public class TreatmentConverter implements Converter<Treatment> {

    private TreatmentService service;

    @Inject
    public TreatmentConverter(TreatmentService service) {
        this.service = service;
    }

    @Override
    public Treatment getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return service.findTreatment(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Treatment treatment) {
        if (treatment == null) {
            return "";
        }
        return Integer.toString(treatment.getId());
    }
}
