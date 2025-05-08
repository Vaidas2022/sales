package lt.ca.javau12.sales.mappers;

import org.springframework.stereotype.Component;

import lt.ca.javau12.sales.dto.CustomerDTO;
import lt.ca.javau12.sales.entities.Customer;

@Component
public class CustomerMapper {

	public CustomerDTO toDto(Customer entity) {
		return new CustomerDTO(
				entity.getId(),
				entity.getName(),
				entity.getEmail(),
				//     /customers/{id}/avatar
				"/customers/" +  entity.getId() + "/avatar"
				);
	}
	
	public Customer toEntity(CustomerDTO dto) {
		return new Customer(
				dto.getName(),
				dto.getEmail()
				);
	}
	public Customer toEntity(Long id, CustomerDTO dto) {
		return new Customer(
				id,
				dto.getName(),
				dto.getEmail()
				);
	}
	
}
