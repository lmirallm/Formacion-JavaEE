package cars.boundaries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cars.entity.Car;

@Stateless
public class CarService {
	@PersistenceContext
	private EntityManager em;

	public List<Car> getCars() {
		Query query = em.createQuery("Select c from car c");
		List<Car> list = new ArrayList<Car>();
		list=query.getResultList();
		return list;
	}

	public Car getCar(UUID id) {
		Car car = em.find(Car.class, id);
		return car;
	}

	public String deleteCar(UUID id) {
		Car carToRemove = this.getCar(id);
		this.em.remove(carToRemove);
		return carToRemove.getBrand() + " eliminado correctamente";
	}

	public Car createCar(Car car) {
		this.em.persist(car);
		this.em.flush();
		this.em.refresh(car);
		return car;
	}

	public Car updateCar(Car car) {
		Car carToUpdate = this.getCar(car.getId());
		carToUpdate.setBrand(car.getBrand());
		carToUpdate.setCountry(car.getCountry());
		carToUpdate.setCreated_at(car.getCreated_at());
		carToUpdate.setLast_updated(car.getLast_updated());
		carToUpdate.setRegistration(car.getRegistration());
		carToUpdate.setId(car.getId());
		return carToUpdate;
	}
}
