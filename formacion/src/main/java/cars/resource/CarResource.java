package cars.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import car.util.ValidatorUtil;
import cars.service.CarService;
import cars.entity.Car;

@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {

	@EJB
	private CarService carService;
	private Car exampleCar;

	@GET
	public Response getAllCars() {
		return Response.status(Status.OK).entity(carService.getCars()).build();
	}

	@GET
	@Path("{id}")
	public Response getCarById(@PathParam("id") final String id) {
		exampleCar = carService.getCar(id);
		if (exampleCar == null) {
			return Response.status(Status.BAD_REQUEST).entity("Could not get car").build();
		} else {
			return Response.status(Status.OK).entity(exampleCar).build();
		}

	}

	@POST
	public Response createCar(Car car) {

		List<String> validationErrors = ValidatorUtil.validate(car);
		if (validationErrors.isEmpty()) {
			return Response.status(Status.OK).entity(carService.createCar(car)).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Could not create car").build();
		}
	}

	@PUT
	@Path("{id}")
	public Response updateCarById(@PathParam("id") String id, Car car) {
		if(carService.updateCar(id, car))
		return Response.status(Status.OK).entity(car).build();
		else
		return Response.status(Status.BAD_REQUEST).entity("Could not update car").build();	
	}

	@DELETE
	@Path("{id}")
	public Response removeCarById(@PathParam("id") String id) {
		if (carService.deleteCar(id))
			return Response.status(Status.OK).entity("Deleted").build();
		else
			return Response.status(Status.BAD_REQUEST).entity("Could not be deleted").build();
	}
}
