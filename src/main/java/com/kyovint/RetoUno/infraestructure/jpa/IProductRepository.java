package com.kyovint.RetoUno.infraestructure.jpa;

import org.springframework.data.repository.CrudRepository;
//Instance of JPA
public interface IProductRepository extends CrudRepository<ProductJPA, Integer> {

}
