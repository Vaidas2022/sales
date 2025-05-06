package lt.ca.javau12.sales.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	
	private String name;
	private String email;
	
	public CustomerDTO() {}

	public CustomerDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}



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

	
	
}
