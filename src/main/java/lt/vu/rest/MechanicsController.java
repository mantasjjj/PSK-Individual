package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Mechanic;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.MechanicDAO;
import lt.vu.rest.contracts.ClientDto;
import lt.vu.rest.contracts.MechanicDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/mechanics")
public class MechanicsController {

    @Inject
    @Setter
    @Getter
    private MechanicDAO mechanicDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Mechanic mechanic = mechanicDAO.findOne(id);
        if (mechanic == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        MechanicDto mechanicDto = new MechanicDto();
        mechanicDto.setName(mechanic.getName());
        mechanicDto.setRole(mechanic.getRole());
        mechanicDto.setClients(mechanic.getClients());

        return Response.ok(mechanicDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer mechanicId, MechanicDto mechanicData) {
        try {
            Mechanic existingMechanic = mechanicDAO.findOne(mechanicId);
            if (existingMechanic == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingMechanic.setName(mechanicData.getName());
            existingMechanic.setRole(mechanicData.getRole());
            existingMechanic.setClients(mechanicData.getClients());
            mechanicDAO.update(existingMechanic);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
