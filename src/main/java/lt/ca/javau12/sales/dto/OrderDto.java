package lt.ca.javau12.sales.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(Long id, LocalDateTime createdAt, String customerName, List<OrderItemResponseDto> items) {}
