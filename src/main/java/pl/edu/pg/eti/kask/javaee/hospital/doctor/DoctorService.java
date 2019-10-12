package pl.edu.pg.eti.kask.javaee.hospital.doctor;

import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
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

    public DoctorService(){
    }

    @PostConstruct
    public void init() {
        doctors.add(new Doctor(1, "Jan", "Kowalski"));
        doctors.add(new Doctor(2, "Piotr", "Nowak"));
        doctors.add(new Doctor(3, "Iwona", "Kaminska"));
        doctors.add(new Doctor(4, "Barbara", "Nowicka"));
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
}
