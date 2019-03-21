package cars.boundaries.resource;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cars.entity.Car;
@Path("cars")
@RequestScoped
public class CarResource {

	@Inject
	private Car exampleCar;
	
	@GET
	public String getAllCars() {
		
		return "";	}
//	@Path()
//	@GET
	
	
}
