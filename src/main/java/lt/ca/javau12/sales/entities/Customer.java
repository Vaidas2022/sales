package lt.ca.javau12.sales.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lt.ca.javau12.sales.dto.CustomerDto;

@Entity
@Table(name="customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	
	
	@OneToMany( mappedBy = "customer", cascade = CascadeType.ALL  )
	private List<Order> orders = new ArrayList<>();
	
	public Customer() {}
	

	
	public Customer(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Customer(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	
	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
}
