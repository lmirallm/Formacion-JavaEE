package cars.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cars.entity.Car;

@Stateless
public class CarService {
	private final static Logger LOGGER = Logger.getLogger(CarService.class.getName());
	@PersistenceContext(unitName = "em_pu")
	private EntityManager em;


	public List<Car> getCars() {
		return (List<Car>) em.createQuery("SELECT c FROM Car c").getResultList();
	}

	public Car getCar(String id) {
		Car car = em.find(Car.class, id);
		return car;
	}

	public boolean deleteCar(String id) {
		Car carToRemove = this.getCar(id);
		if (carToRemove != null) {
			this.em.remove(carToRemove);
			return true;
		} else {
			return false;
		}
	}

	public Car createCar(Car car) {
		this.em.persist(car);
		this.em.flush();
		this.em.refresh(car);
		return car;
	}
	

	public boolean updateCar(String id, Car car) {
		Car dbCar=getCar(id);
		if(dbCar !=null) {
			car.setId(id);
			car.setCreated_at(dbCar.getCreated_at());
			car.setRegistration(dbCar.getRegistration());
			this.em.merge(car);
			return true;
		}else{
			return false;
		}
		
	}
}
