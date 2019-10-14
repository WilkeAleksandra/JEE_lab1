package pl.edu.pg.eti.kask.javaee.hospital.treatment;

import pl.edu.pg.eti.kask.javaee.hospital.DataService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.DoctorService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    @Inject
    private DoctorService doctorService;

    @Inject
    private DataService dataService;

    public TreatmentService() {
    }

    /**
     * @return all available treatments
     */
    public synchronized List<Treatment> findAllTreatments() {
        return treatments.stream().map(Treatment::new).collect(Collectors.toList());
    }

    /**
     * @param id book id
     * @return single book or null if empty
     */
    public synchronized Treatment findTreatment(int id) {
        return treatments.stream()
                .filter(treatment -> treatment.getId() == id)
                .findFirst()
                .map(Treatment::new)
                .orElse(null);
    }

    public synchronized void saveTreatment(Treatment treatment) {
        if (treatment.getId() != 0) {
            treatments.removeIf(b -> b.getId() == treatment.getId());
            treatments.add(new Treatment(treatment));
        } else {
            treatment.setId(treatments.stream().mapToInt(Treatment::getId).max().orElse(0) + 1);
            treatments.add(new Treatment(treatment));
        }
    }

    public void removeTreatment(Treatment treatment) {
        treatments.removeIf(b -> b.equals(treatment));
    }

    @PostConstruct
    public void init() {
        treatments.addAll(dataService.getTreatmentList());
    }

    public synchronized List<Doctor> findAllDoctors() {
        return doctorService.findAllDoctors();
    }
}
