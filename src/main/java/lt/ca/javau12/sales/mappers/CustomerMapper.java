package lt.ca.javau12.sales.mappers;

import org.springframework.stereotype.Component;

import lt.ca.javau12.sales.dto.CustomerDto;
import lt.ca.javau12.sales.entities.Customer;

@Component
public class CustomerMapper {

	public CustomerDto toDto(Customer entity) {
		return new CustomerDto(
				entity.getId(),
				entity.getName(),
				entity.getEmail()
				);
	}
	
	public Customer toEntity(CustomerDto dto) {
		return new Customer(
				dto.getName(),
				dto.getEmail()
				);
	}
	public Customer toEntity(Long id, CustomerDto dto) {
		return new Customer(
				id,
				dto.getName(),
				dto.getEmail()
				);
	}
	
}
