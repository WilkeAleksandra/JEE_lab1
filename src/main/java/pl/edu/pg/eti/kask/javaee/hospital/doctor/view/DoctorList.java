package pl.edu.pg.eti.kask.javaee.hospital.doctor.view;

import pl.edu.pg.eti.kask.javaee.hospital.doctor.DoctorService;
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
    private DoctorService service;

    /**
     * Lazy loaded list of books.
     */
    private List<Doctor> doctors;

    @Inject
    public DoctorList(DoctorService service) {
        this.service = service;
    }

    /**
     * @return all books in storage
     */
    public List<Doctor> getDoctors() {
        if (doctors == null) {
            doctors = service.findAllDoctors();
        }
        return doctors;
    }

    /**
     * Deletes doctor from the storage.
     *
     * @param doctor doctor to be deleted
     * @return navigation url
     */
    public String removeDoctor(Doctor doctor) {
        service.removeDoctor(doctor);
        return "doctor_list?faces-redirect=true";
    }
}
