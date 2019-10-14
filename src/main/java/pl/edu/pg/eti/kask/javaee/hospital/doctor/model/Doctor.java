package pl.edu.pg.eti.kask.javaee.hospital.doctor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Doctor responsible for treatment.
 *
 * @author ewatusien
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements Serializable {

    /**
     * Doctor id. Database surrogate key.
     */
    private int id;

    /**
     * Doctor name.
     */
    private String name;

    /**
     * Doctor surname.
     */
    private String surname;

    private MedicalSpecialization specialization;

    /**
     * Cloning constructor.
     *
     * @param doctor cloned doctor
     */
    public Doctor(Doctor doctor) {
        this.id = doctor.id;
        this.name = doctor.name;
        this.surname = doctor.surname;
        this.specialization = doctor.specialization;
    }


}
