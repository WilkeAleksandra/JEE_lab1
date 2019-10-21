package pl.edu.pg.eti.kask.javaee.hospital.doctor.resource;

import pl.edu.pg.eti.kask.javaee.hospital.doctor.DoctorService;
import pl.edu.pg.eti.kask.javaee.hospital.doctor.model.Doctor;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.resource.TreatmentResource;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Collection;

@Path("/doctors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorResource {

    @Inject
    DoctorService doctorService;

    /**
     * Find single doctor.
     *
     * @param id doctor id
     * @return response with book entity or 404 code
     */
    @GET
    @Path("{doctorId}")
    public Response getTreatment(@PathParam("doctorId") @Min(1) int id) {
        Doctor doctor = doctorService.findDoctor(id);
        if (doctor != null) {
            return Response.ok(doctor).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Deletes single doctor.
     *
     * @param id doctor id
     * @return response 204 code or 404 when doctor does not exist
     */
    @DELETE
    @Path("{doctorId}")
    public Response deleteTreatment(@PathParam("doctorId") @Min(1) int id) {
        Doctor original = doctorService.findDoctor(id);
        if (original == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            doctorService.removeDoctor(original);
            return Response.noContent().build();
        }
    }

    /**
     * Fetch all doctors available.
     *
     * @return all doctors
     */
    @GET
    public Response getTreatments() {
        Collection<Doctor> treatments = doctorService.findAllDoctors();
        return Response.ok(treatments).build();
    }

    /**
     * Updates single treatment.
     *
     * @param id     doctor id
     * @param doctor updated doctor
     * @return response 200 code or 404 when doctor does not exist or 400 when doctors ids mismatch
     */
    @PUT
    @Path("{doctorId}")
    public Response updateTreatment(@PathParam("doctorId") int id, Doctor doctor) {
        Doctor original = doctorService.findDoctor(id);
        if (original == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (original.getId() != doctor.getId()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            doctorService.saveDoctor(doctor);
            return Response.ok().build();
        }
    }

    /**
     * Saves new treatment.
     *
     * @param doctor new treatment
     * @return response with 201 code and new object uri
     */
    @POST
    public Response saveTreatment(Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return Response
                .created(
                        UriBuilder
                                .fromResource(TreatmentResource.class)
                                .path(TreatmentResource.class, "getTreatment")
                                .build(doctor.getId()))
                .build();
    }

}
