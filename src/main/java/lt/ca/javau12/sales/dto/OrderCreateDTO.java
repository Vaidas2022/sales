package lt.ca.javau12.sales.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OrderCreateDTO(
		@NotNull  Long customerId, 
		@NotEmpty List<@Valid OrderItemDTO> items) {}
