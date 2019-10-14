package pl.edu.pg.eti.kask.javaee.hospital.doctor;

import pl.edu.pg.eti.kask.javaee.hospital.DataService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service bean for managing doctors.
 *
 * @author ewatusien
 */
@ApplicationScoped
public class DoctorService {

    /**
     * All availiable doctors.
     */
    private final List<Doctor> doctors = new ArrayList<>();

    @Inject
    private DataService dataService;

    public DoctorService() {
    }

    /**
     * @return all available doctors
     */
    public synchronized List<Doctor> findAllDoctors() {
        return doctors.stream().map(Doctor::new).collect(Collectors.toList());
    }

    /**
     * Saves new doctor.
     *
     * @param doctor doctor to be saved
     */
    public synchronized void saveDoctor(Doctor doctor) {
        if (doctor.getId() != 0) {
            doctors.removeIf(b -> b.getId() == doctor.getId());
            doctors.add(new Doctor(doctor));
        } else {
            doctor.setId(doctors.stream().mapToInt(Doctor::getId).max().orElse(0) + 1);
            doctors.add(new Doctor(doctor));
        }
    }

    /**
     * @param id doctor id
     * @return single doctor or null if empty
     */
    public synchronized Doctor findDoctor(int id) {
        return doctors.stream().filter(doctor -> doctor.getId() == id).findFirst().map(Doctor::new).orElse(null);
    }

    public void removeDoctor(Doctor doctor) {
        doctors.removeIf(b -> b.equals(doctor));
    }

    @PostConstruct
    public void init() {
        doctors.addAll(dataService.getDoctorList());
    }
}
