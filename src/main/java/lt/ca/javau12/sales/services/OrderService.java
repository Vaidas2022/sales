package lt.ca.javau12.sales.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lt.ca.javau12.sales.dto.OrderCreateDTO;
import lt.ca.javau12.sales.dto.OrderDto;
import lt.ca.javau12.sales.dto.OrderItemResponseDto;
import lt.ca.javau12.sales.entities.Customer;
import lt.ca.javau12.sales.entities.Goods;
import lt.ca.javau12.sales.entities.Order;
import lt.ca.javau12.sales.entities.OrderItem;
import lt.ca.javau12.sales.repositories.CustomerRepository;
import lt.ca.javau12.sales.repositories.GoodsRepository;
import lt.ca.javau12.sales.repositories.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final GoodsRepository goodsRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        GoodsRepository goodsRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.goodsRepository = goodsRepository;
    }

    public Order createOrder(OrderCreateDTO dto) {
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = dto.items().stream().map(itemDto -> {
            Goods goods = goodsRepository.findById(itemDto.goodsId())
                    .orElseThrow(() -> new RuntimeException("Goods not found"));

            OrderItem item = new OrderItem();
            item.setGoods(goods);
            item.setQuantity(itemDto.quantity());
            item.setPriceAtOrderTime(goods.getPrice());
            item.setOrder(order);
            return item;
        }).toList();

        order.setItems(items);

        return orderRepository.save(order);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItemResponseDto> itemDtos = order.getItems().stream()
                .map(item -> new OrderItemResponseDto(
                        item.getGoods().getName(),
                        item.getPriceAtOrderTime(),
                        item.getQuantity()
                )).toList();

        return new OrderDto(
                order.getId(),
                order.getCreatedAt(),
                order.getCustomer().getName(),
                itemDtos
        );
    }
}
