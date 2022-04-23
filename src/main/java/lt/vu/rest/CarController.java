package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.Client;
import lt.vu.persistence.CarDAO;
import lt.vu.persistence.ClientDAO;
import lt.vu.rest.contracts.CarDto;
import lt.vu.rest.contracts.ClientDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/cars")
public class CarController {

    @Inject
    @Setter
    @Getter
    private CarDAO carDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Car car = carDAO.findOne(id);
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CarDto carDto = new CarDto();
        carDto.setMake(car.getMake());
        carDto.setModel(car.getModel());
        carDto.setYear(car.getYear());
        carDto.setLicencePlate(car.getLicencePlate());
        carDto.setOwner(car.getOwner());

        return Response.ok(carDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer carId, CarDto carData) {
        try {
            Car existingCar = carDAO.findOne(carId);
            if (existingCar == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCar.setMake(carData.getMake());
            existingCar.setModel(carData.getModel());
            existingCar.setYear(carData.getYear());
            existingCar.setLicencePlate(carData.getLicencePlate());
            existingCar.setOwner(carData.getOwner());

            carDAO.update(existingCar);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
