package cars.boundaries.resource;


import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import cars.boundaries.service.CarService;
import cars.entity.Car;
@Path("cars")
@RequestScoped
public class CarResource {

	@Inject
	private CarService carService;
	private Car exampleCar;
	
	@GET
	public String getAllCars() {
		carService.getCars();
		return "";	}
	
	@GET
	@Path("{id}")
	public Car getCarById(@PathParam("id") UUID id) {
		exampleCar=carService.getCar(id);
		return exampleCar;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String createCar(Car car) {
		return carService.createCar(car).getBrand()+" creado";
	}
	
	@PUT
	@Path("{id}")
	public String updateCarById(@PathParam("id") UUID id) {
		exampleCar=this.getCarById(id);
		return carService.updateCar(exampleCar).getBrand() + " ha sido actualizado";
	}
	
	
	@DELETE
	@Path("{id}")
	public String removeCarById(@PathParam("id") UUID id) {	
		return carService.deleteCar(id);
	}
}
