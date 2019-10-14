package pl.edu.pg.eti.kask.javaee.hospital;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.MedicalSpecialization;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Status;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DataService {

    @Getter
    @Setter
    private final List<Doctor> doctorList = new ArrayList<>();
    @Getter
    @Setter
    private final List<Treatment> treatmentList  = new ArrayList<>();


    public DataService(){

    }
    @PostConstruct
    public void init() {

        doctorList.add(new Doctor(1, "Jan", "Kowalski", MedicalSpecialization.OCULIST));
        doctorList.add(new Doctor(2, "Piotr", "Nowak", MedicalSpecialization.LARYNGOLOGIST));
        doctorList.add(new Doctor(3, "Iwona", "Kaminska", MedicalSpecialization.PEDIATRIST));
        doctorList.add(new Doctor(4, "Barbara", "Nowicka", MedicalSpecialization.PEDIATRIST));

        treatmentList.add(new Treatment(1,
                                   "Badanie wzroku",
                                         LocalDate.of(2013, 10, 11),
                                         LocalTime.of(10,00,00),
                                         Status.COMPLETE,
                                         List.of(doctorList.get(0))));
        treatmentList.add(new Treatment(2,
                "Badanie USG",
                LocalDate.of(2019, 9, 15),
                LocalTime.of(10,00,00),
                Status.CANCELED,
                List.of(doctorList.get(1))));
    }
}
