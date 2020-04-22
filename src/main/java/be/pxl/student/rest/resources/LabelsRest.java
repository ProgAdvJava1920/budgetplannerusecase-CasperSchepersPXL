package be.pxl.student.rest.resources;

import be.pxl.student.DuplicateLabelException;
import be.pxl.student.LabelInUseException;
import be.pxl.student.LabelNotFoundException;
import be.pxl.student.entity.Label;
import be.pxl.student.service.LabelService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/labels")
public class LabelsRest {

    @Inject
    private LabelService labelService;

    @GET
    @Produces("applications/json")
    public Response getLabels() {
        List<Label> labels = labelService.findAllLabels();
        return Response.ok(labels).build();
    }

    @POST
    public Response addLabel(LabelCreateResource labelCreateResource) {
        try {
            labelService.addLabel(labelCreateResource.getName());
            return Response.created(UriBuilder.fromPath("/labels/").build()).build();
        } catch (DuplicateLabelException e) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeLabel(@PathParam("id") long labelId) {
        try {
            labelService.removeLabel(labelId);
        } catch (LabelNotFoundException | LabelInUseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(e)).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
