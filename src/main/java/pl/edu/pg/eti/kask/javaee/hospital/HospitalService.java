package pl.edu.pg.eti.kask.javaee.hospital;

import pl.edu.pg.eti.kask.javaee.hospital.doctor.DoctorService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.TreatmentService;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Status;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class HospitalService {

    private DoctorService doctorService;
    private TreatmentService treatmentService ;

    @Inject
    public HospitalService(DoctorService doctorService, TreatmentService treatmentService) {
        this.doctorService = doctorService;
        this.treatmentService = treatmentService;
    }

    public HospitalService(){

    }
    @PostConstruct
    public void init() {
        doctorService.saveDoctor(new Doctor(1, "Jan", "Kowalski"));
        doctorService.saveDoctor(new Doctor(2, "Piotr", "Nowak"));
        doctorService.saveDoctor(new Doctor(3, "Iwona", "Kaminska"));
        doctorService.saveDoctor(new Doctor(4, "Barbara", "Nowicka"));

        treatmentService.saveTreatment(new Treatment(1,
                                   "Badanie wzroku",
                                         LocalDate.of(2013, 10, 11),
                                         LocalTime.of(10,00,00),
                                         Status.COMPLETE,
                                         List.of(doctorService.findDoctor(1))));
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public TreatmentService getTreatmentService() {
        return treatmentService;
    }
}
