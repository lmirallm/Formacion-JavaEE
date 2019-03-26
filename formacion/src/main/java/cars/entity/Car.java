package cars.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;


@Entity
@Table(name="car")
public class Car {
	@Id
//	@NotNull(message="The ID is null")
	private UUID id;
//	@NotBlank(message="Do not leave the brand empty")
	private String brand;
	@Temporal(TemporalType.TIMESTAMP)
//	@PastOrPresent(message="Use a correct date")
//	@NotNull(message="The registration date is null")
	private Date registration;
//	@Min(3)
//	@NotBlank(message="The country is empty")
	private String country;
	@Temporal(TemporalType.TIMESTAMP)
//	@PastOrPresent(message="Date is incorrect, use a correct one")
//	@NotNull(message="The creation date is null")
	private Date created_at;
	@Temporal(TemporalType.TIMESTAMP)
//	@PastOrPresent(message="Date is incorrect, use the actual one")
//	@NotNull(message="The last update date is null")
	private Date last_updated;
	

	public Car() {
		
	}
	
	
	public Car(UUID id, String brand, Date registration, String country, Date created_at, Date last_updated) {
		this.id = id;
		this.brand = brand;
		this.registration = registration;
		this.country = country;
		this.created_at = created_at;
		this.last_updated = last_updated;
	}


	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Date getRegistration() {
		return registration;
	}
	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Date getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Timestamp last_updated) {
		this.last_updated = last_updated;
	}
	
	
	
}
