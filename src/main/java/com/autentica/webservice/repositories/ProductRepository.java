package com.autentica.webservice.repositories;

import com.autentica.webservice.models.Order;
import com.autentica.webservice.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
