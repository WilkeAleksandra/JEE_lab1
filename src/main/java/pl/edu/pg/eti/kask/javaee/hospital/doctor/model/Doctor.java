package pl.edu.pg.eti.kask.javaee.hospital.doctor.model;

import lombok.*;

import java.io.Serializable;

/**
 * Doctor responsible for treatment.
 *
 * @author ewatusien
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
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
