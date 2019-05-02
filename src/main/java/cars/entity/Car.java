package cars.entity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "brand", nullable = false, length = 50)
	@NotNull(message = "Do not leave the brand empty")
	@Size(min = 3, max = 50, message = "The size must be between 3 and 50 characters")
	private String brand;
	@Column(name = "registration", nullable = false)
	private Date registration;
	@Column(name = "country", nullable = false, length = 50)
	@Size(min = 3, max = 30, message = "The size must be between 3 and 50 characters")
	@NotNull(message = "The country is empty")
	private String country;
	@Column(name = "createdAt", updatable=false, nullable = false)
	private Date created_at;
	@Column(name = "last_updated")
	private Date last_updated;
	@Column(name="checked")
	private Boolean checked;
	
	
	@PrePersist
	protected void onCreate() {
		Date now = getISO8601Date();
		created_at = now;
		last_updated = now;
		id = UUID.randomUUID().toString();
		checked=false;
	}

	@PreUpdate
	protected void onUpdate() {
		last_updated = getISO8601Date();
	}

	private static Date getISO8601Date() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date actualTime = new Date();
		String target = dateFormat.format(actualTime);
		DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
		TemporalAccessor accessor = timeFormatter.parse(target);
		Date now = Date.from(Instant.from(accessor));
		return now;
	}

	public Car() {

	}

	public Car(String id, String brand, Timestamp registration, String country, Date created_at, Date last_updated,Boolean checked) {
		this.id = id;
		this.brand = brand;
		this.registration = registration;
		this.country = country;
		this.created_at = created_at;
		this.last_updated = last_updated;
		this.checked=checked;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public void setRegistration(Date registration) {
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

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

}
