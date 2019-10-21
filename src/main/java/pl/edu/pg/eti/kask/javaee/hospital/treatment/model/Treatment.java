package pl.edu.pg.eti.kask.javaee.hospital.treatment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Just a treatment.
 *
 * @author ewatusien
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treatment implements Serializable {


    /**
     * Treatment id. Database surrogate key.
     */
    private int id;

    /**
     * Treatment type.
     */
    private String type;

    /**
     * Treatment date.
     */
    //@JsonbDateFormat(value="yyyy-MM-dd")
    private LocalDate date;

    /**
     * Treatment date.
     */
    private LocalTime time;

    /**
     * Treatment status.
     */
    private Status status;

    /**
     * Doctors responsible for treatment.
     */
    private List<Doctor> doctors;

    /**
     * Cloning constructor.
     *
     * @param treatment cloned treatment
     */
    public Treatment(Treatment treatment) {
        this.id = treatment.id;
        this.type = treatment.type;
        this.date = treatment.date;
        this.time = treatment.time;
        this.status = treatment.status;
        this.doctors = treatment.doctors.stream().map(Doctor::new).collect(Collectors.toList());
    }
}
