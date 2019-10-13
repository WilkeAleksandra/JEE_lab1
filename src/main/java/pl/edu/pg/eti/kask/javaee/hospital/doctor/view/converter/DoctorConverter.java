package pl.edu.pg.eti.kask.javaee.hospital.doctor.view.converter;

import pl.edu.pg.eti.kask.javaee.hospital.doctor.DoctorService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Doctor.class, managed = true)
@Dependent
public class DoctorConverter implements Converter<Doctor> {

    private DoctorService service;

    @Inject
    public DoctorConverter(DoctorService service) {
        this.service = service;
    }

    @Override
    public Doctor getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return service.findDoctor(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Doctor doctor) {
        if (doctor == null) {
            return "";
        }
        return Integer.toString(doctor.getId());
    }
}
