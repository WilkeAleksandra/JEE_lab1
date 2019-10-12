package pl.edu.pg.eti.kask.javaee.hospital.doctor.view;

import pl.edu.pg.eti.kask.javaee.hospital.HospitalService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class DoctorList {

    /**
     * Injected doctor service.
     */
    private HospitalService service;

    /**
     * Lazy loaded list of books.
     */
    private List<Doctor> doctors;

    @Inject
    public DoctorList(HospitalService service) {
        this.service = service;
}
    /**
     * @return all books in storage
     */
    public List<Doctor> getDoctors() {
        if (doctors == null) {
            doctors = service.getDoctorService().findAllDoctors();
        }
        return doctors;
    }
}
