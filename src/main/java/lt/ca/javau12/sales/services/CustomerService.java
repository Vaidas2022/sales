package lt.ca.javau12.sales.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lt.ca.javau12.sales.controllers.CustomerController;
import lt.ca.javau12.sales.dto.CustomerDTO;
import lt.ca.javau12.sales.entities.Customer;
import lt.ca.javau12.sales.mappers.CustomerMapper;
import lt.ca.javau12.sales.repositories.CustomerRepository;

@Service
public class CustomerService {

 
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	public CustomerService(
			CustomerRepository customerRepository,
			CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	public List<CustomerDTO> getAll() {
		
		return customerRepository
			.findAll()
			.stream()
			.map( e -> customerMapper.toDto(e))
			.toList();		

	}

	public Optional<CustomerDTO> byId(Long id) {
	
		return customerRepository
				.findById(id)
				.map(e -> customerMapper.toDto(e));
	}

	public CustomerDTO create(CustomerDTO dto) {
		Customer entity = customerMapper.toEntity(dto);
		return  customerMapper
				.toDto(customerRepository.save(entity));	
	}

	public void uploadAvatar(Long id, MultipartFile file) throws IOException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("Customer not found"));
		
		customer.setImage( file.getBytes() );
		customerRepository.save(customer);
	}

	public byte[] getAvatar(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("Customer not found"));				
		return customer.getImage();
	}

}
