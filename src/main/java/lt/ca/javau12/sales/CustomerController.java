package lt.ca.javau12.sales;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau12.sales.dto.CustomerDto;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerDto>> getAll(){
		return ResponseEntity.ok( customerService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id){
		return ResponseEntity.of( customerService.byId(id) );
	}
	
	@PostMapping
	public ResponseEntity<CustomerDto> create( @RequestBody CustomerDto dto) {
		return ResponseEntity
				.status(HttpStatusCode.valueOf(201))
				.body(customerService.create(dto));
	}
	
	
}
