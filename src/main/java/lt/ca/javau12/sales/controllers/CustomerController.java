package lt.ca.javau12.sales.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lt.ca.javau12.sales.dto.CustomerDTO;
import lt.ca.javau12.sales.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAll(){
		return ResponseEntity.ok( customerService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
		return ResponseEntity.of( customerService.byId(id) );
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO dto) {
		return ResponseEntity
				.status(HttpStatusCode.valueOf(201))
				.body(customerService.create(dto));
	}
	
	@PostMapping("/{id}/avatar")
	public ResponseEntity<Void> uploadAvatar(
			@PathVariable Long id, 
			@RequestParam MultipartFile file) throws IOException{
		
		customerService.uploadAvatar(id, file);
			
		return ResponseEntity.ok().build();

	}
	
	
	@GetMapping("/{id}/avatar") ///customers/{id}/avatar
	public ResponseEntity<byte[]> getAvatar(@PathVariable Long id) {
		byte[] image = customerService.getAvatar(id);
		return ResponseEntity.ok()
				.contentType( MediaType.IMAGE_JPEG )
				.body(image);
				
	}
	
}
