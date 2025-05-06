package lt.ca.javau12.sales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau12.sales.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
