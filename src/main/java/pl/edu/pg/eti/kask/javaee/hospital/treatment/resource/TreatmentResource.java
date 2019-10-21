package pl.edu.pg.eti.kask.javaee.hospital.treatment.resource;

import pl.edu.pg.eti.kask.javaee.hospital.treatment.TreatmentService;
import pl.edu.pg.eti.kask.javaee.hospital.treatment.model.Treatment;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Collection;

@Path("/treatments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TreatmentResource {

    @Inject
    TreatmentService treatmentService;

    /**
     * Find single treatment.
     *
     * @param id treatment id
     * @return response with treatment entity or 404 code
     */
    @GET
    @Path("{treatmentId}")
    public Response getTreatment(@PathParam("treatmentId") @Min(1) int id) {
        Treatment treatment = treatmentService.findTreatment(id);
        if (treatment != null) {
            return Response.ok(treatment).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Deletes single treatment.
     *
     * @param id treatment id
     * @return response 204 code or 404 when treatment does not exist
     */
    @DELETE
    @Path("{treatmentId}")
    public Response deleteTreatment(@PathParam("treatmentId") @Min(1) int id) {
        Treatment original = treatmentService.findTreatment(id);
        if (original == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            treatmentService.removeTreatment(original);
            return Response.noContent().build();
        }
    }

    /**
     * Fetch all treatments available.
     *
     * @return all treatments
     */
    @GET
    public Response getTreatments() {
        Collection<Treatment> treatments = treatmentService.findAllTreatments();
        return Response.ok(treatments).build();
    }

    /**
     * Updates single treatment.
     *
     * @param id treatment id
     * @param treatment updated treatment
     * @return response 200 code or 404 when treatment does not exist or 400 when treatments ids mismatch
     */
    @PUT
    @Path("{treatmentId}")
    public Response updateTreatment(@PathParam("treatmentId") int id, Treatment treatment) {
        Treatment original = treatmentService.findTreatment(id);
        if (original == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (original.getId() != treatment.getId()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            treatmentService.saveTreatment(treatment);
            return Response.ok().build();
        }
    }

    /**
     * Saves new treatment.
     *
     * @param treatment new treatment
     * @return response with 201 code and new object uri
     */
    @POST
    public Response saveTreatment(Treatment treatment) {
        treatmentService.saveTreatment(treatment);
        return Response
                .created(
                        UriBuilder
                                .fromResource(TreatmentResource.class)
                                .path(TreatmentResource.class, "getTreatment")
                                .build(treatment.getId()))
                .build();
    }

}
