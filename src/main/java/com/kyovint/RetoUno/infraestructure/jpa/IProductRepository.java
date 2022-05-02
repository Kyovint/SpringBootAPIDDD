package com.kyovint.RetoUno.infraestructure.jpa;


import org.springframework.data.jpa.repository.JpaRepository;


//Instance of JPA
public interface IProductRepository extends JpaRepository<ProductJPA, Integer> {

}
