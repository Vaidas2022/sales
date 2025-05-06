package lt.ca.javau12.sales;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import lt.ca.javau12.sales.dto.CustomerDto;
import lt.ca.javau12.sales.entities.Customer;
import lt.ca.javau12.sales.mappers.CustomerMapper;
import lt.ca.javau12.sales.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	public CustomerService(
			CustomerRepository customerRepository,
			CustomerMapper customerMapper
			) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	public List<CustomerDto> getAll() {
		
		return customerRepository
			.findAll()
			.stream()
			.map( e -> customerMapper.toDto(e))
			.toList();		

	}

	public Optional<CustomerDto> byId(Long id) {
		return customerRepository
				.findById(id)
				.map(e -> customerMapper.toDto(e));
	}

	public CustomerDto create(CustomerDto dto) {
		Customer entity = customerMapper.toEntity(dto);
		return  customerMapper
				.toDto(customerRepository.save(entity));	
	}

}
