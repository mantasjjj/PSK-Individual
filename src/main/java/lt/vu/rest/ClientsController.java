package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Mechanic;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.MechanicDAO;
import lt.vu.rest.contracts.ClientDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/clients")
public class ClientsController {

    @Inject
    @Setter
    @Getter
    private ClientDAO clientDAO;

    @Inject
    @Setter
    @Getter
    private MechanicDAO mechanicDAO;

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createClient(ClientDto clientData) {
        try {
            Client newClient = new Client();
            newClient.setName(clientData.getName());
            newClient.setCarMake(clientData.getCarMake());
            newClient.setCarLicencePlate(clientData.getLicencePlate());

            Mechanic mechanic = mechanicDAO.findOne(clientData.getMechanicId());

            if (mechanic != null) {
                newClient.setMechanic(mechanic);
            }

            clientDAO.persist(newClient);

            return Response.ok(newClient).build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Client client = clientDAO.findOne(id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ClientDto clientDto = new ClientDto();
        clientDto.setName(client.getName());
        clientDto.setCar(clientDto.getCar());

        return Response.ok(clientDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer clientId, ClientDto clientData) {
        try {
            Client existingClient = clientDAO.findOne(clientId);
            if (existingClient == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingClient.setName(clientData.getName());
            clientDAO.update(existingClient);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
