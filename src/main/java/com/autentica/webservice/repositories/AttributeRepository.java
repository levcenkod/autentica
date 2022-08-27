package com.autentica.webservice.repositories;

import com.autentica.webservice.models.Attribute;
import com.autentica.webservice.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface AttributeRepository extends CrudRepository<Attribute, Long> {
}
