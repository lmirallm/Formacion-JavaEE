package cars.resource;

import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import cars.entity.Car;
import logger.MyLogger;
@Path("cars")
@Api(value="car")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {
	private final static Logger LOGGER = Logger.getLogger(CarResource.class.getName());
	
    CarResource tester = new CarResource();
       
    
   
	@EJB
	private CarService carService;
	private Car exampleCar;

	/**
	 * 
	 * @return When the request its done , the method call to {@link cars.service.CarService#getCars()}, it returns all the cars in the database. The method returns a response with all the cars in a JSON. 
	 */
	@GET
	 @ApiOperation(value = "Return all cars",
	    response = Car.class,
	    responseContainer = "List")
	public Response getAllCars() {
		return Response.status(Status.OK).entity(carService.getCars()).build();
	}

	/**
	 * 
	 * @param id must be included in the request with a UUID format string.
	 * @return if a car with that id exists, the method {@link cars.service.CarService#getCar(String)}  returns that object from the database. Then a response is sent with the information in a JSON if all went right, if not a BAD REQUEST response is sent.
	 */
	@GET
	@Path("{id}")
	 @ApiOperation(value = "Return car by its ID",
	    response = Car.class,
	    responseContainer = "JSON")
	
	public Response getCarById(@ApiParam(value = "id of the car that need to be returned", required = true) @PathParam("id") final String id) {
		
		exampleCar = carService.getCar(id);
		if (exampleCar == null) {
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe("Car could not be gotten with the ID "+id);
			return Response.status(Status.BAD_REQUEST).entity("Could not get car").build();
		} else {
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe("Car with the ID "+id+" gotten");
			return Response.status(Status.OK).entity(exampleCar).build();
		}

	}
	/**
	 * 
	 * @param car a JSON with all the information referred in {@link cars.entity} must be sent.
	 * @return if the car has been created, the response returned is a 200 with the information of the car in a JSON, but if it has not been possible to create, the response is a 400 BAD REQUEST and a message that the car could not be created. 
	 */
	@POST
	 @ApiOperation(value = "Persiste a car",
	    response = Car.class,
	    responseContainer = "JSON")
	public Response createCar(@ApiParam(value = "Car that need to be created", required = true)  Car car) {
		
		List<String> validationErrors = ValidatorUtil.validate(car);
		if (validationErrors.isEmpty()) {
			return Response.status(Status.OK).entity(carService.createCar(car)).build();
		} else {
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe("Car could not be persisted");
			return Response.status(Status.BAD_REQUEST).entity("Could not create car").build();
		}
	}

	/**
	 * 
	 * @param id must be included in the request with a UUID format string in order to find the car to update.
	 * @param car a JSON with the 
	 * @return
	 */
	@PUT
	@Path("{id}")
	 @ApiOperation(value = "Update a car by its ID",
	    response = Car.class,
	    responseContainer = "JSON")
	public Response updateCarById(@ApiParam(value = "id of the car that need to be updated", required = true) @PathParam("id") String id, Car car) {
		if (carService.updateCar(id, car))
			return Response.status(Status.OK).entity(car).build();
		else
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe("Car could not be updated ");
		
		return Response.status(Status.BAD_REQUEST).entity("Could not update car").build();
	}

	@DELETE
	@Path("{id}")
	
	 @ApiOperation(value = "Remove a car by its ID",
	    response = Car.class,
	    responseContainer = "JSON")
	public Response removeCarById(@ApiParam(value = "id of the car that need to be deleted", required = true) @PathParam("id") String id) {
		if (carService.deleteCar(id))
			return Response.status(Status.OK).entity("Deleted").build();
		else
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe("Car could not be deleted");
		return Response.status(Status.BAD_REQUEST).entity("Could not be deleted").build();
	}
}
