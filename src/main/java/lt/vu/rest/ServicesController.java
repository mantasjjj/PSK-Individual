package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Mechanic;
import lt.vu.entities.Service;
import lt.vu.persistence.MechanicDAO;
import lt.vu.persistence.ServiceDAO;
import lt.vu.rest.contracts.MechanicDto;
import lt.vu.rest.contracts.ServiceDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/services")
public class ServicesController {

    @Inject
    @Setter
    @Getter
    private ServiceDAO serviceDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Service service = serviceDAO.findOne(id);
        if (service == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName(service.getName());
        serviceDto.setMechanics(service.getMechanics());

        return Response.ok(serviceDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer mechanicId, ServiceDto serviceDto) {
        try {
            Service existingService = serviceDAO.findOne(mechanicId);
            if (existingService == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingService.setName(serviceDto.getName());
            existingService.setMechanics(serviceDto.getMechanics());
            serviceDAO.update(existingService);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
