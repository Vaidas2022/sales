package lt.ca.javau12.sales.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lt.ca.javau12.sales.dto.OrderCreateDTO;
import lt.ca.javau12.sales.dto.OrderDto;
import lt.ca.javau12.sales.entities.Order;
import lt.ca.javau12.sales.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody OrderCreateDTO dto) {
    	logger.debug("Got OrderCreateDTO to Post endpoint " + dto );
    	
        Order created = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
} 
