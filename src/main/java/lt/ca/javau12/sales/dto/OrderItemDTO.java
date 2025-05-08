package lt.ca.javau12.sales.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemDTO(
		@NotNull
		Long goodsId, 
		@Min(1)
		Integer quantity) {}
