package com.autentica.webservice.repositories;

import com.autentica.webservice.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {


}
