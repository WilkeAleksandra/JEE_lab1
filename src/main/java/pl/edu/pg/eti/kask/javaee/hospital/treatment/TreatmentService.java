package pl.edu.pg.eti.kask.javaee.hospital.treatment;

import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Status;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service bean for managing treatments.
 *
 * @author ewatusien
 */
@ApplicationScoped
public class TreatmentService {

    /**
     * All available treatments.
     */
    private final List<Treatment> treatments = new ArrayList<>();

    /**
     * All availiable doctors.
     */
    private final List<Doctor> doctors = new ArrayList<>();

    public TreatmentService(){
    }

    @PostConstruct
    public void init() {
        doctors.add(new Doctor(1, "Jan", "Kowalski"));
        doctors.add(new Doctor(2, "Piotr", "Nowak"));
        doctors.add(new Doctor(3, "Iwona", "Kaminska"));
        doctors.add(new Doctor(4, "Barbara", "Nowicka"));

        treatments.add(new Treatment(
                1,
                "Badanie wzroku",
                LocalDate.of(2019, 10, 20),
                LocalTime.of(10, 00, 00),
                Status.INCOMPLETE,
                List.of(doctors.get(2))));

        treatments.add(new Treatment(
                2,
                "Badanie USG",
                LocalDate.of(2019, 12, 10),
                LocalTime.of(12, 15, 00),
                Status.INCOMPLETE,
                List.of(doctors.get(1))));

        treatments.add(new Treatment(
                3,
                "Operacja kolana",
                LocalDate.of(2019, 6, 10),
                LocalTime.of(10, 00, 00),
                Status.COMPLETE,
                List.of(doctors.get(0), doctors.get(3))));

    }

    /**
     * @return all available doctors
     */
    public synchronized List<Doctor> findAllDoctors() {
        return doctors.stream().map(Doctor::new).collect(Collectors.toList());
    }

    /**
     * @return all available treatments
     */
    public synchronized List<Treatment> findAllTreatments() {
        return treatments.stream().map(Treatment::new).collect(Collectors.toList());
    }
}
