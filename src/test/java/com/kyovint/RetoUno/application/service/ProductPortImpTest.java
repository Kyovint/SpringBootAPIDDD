package com.kyovint.RetoUno.application.service;

import com.kyovint.RetoUno.application.ports.output.IProductPersistencePort;
import com.kyovint.RetoUno.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ProductPortImpTest {

    @Mock
    IProductPersistencePort productPersistencePort;


    @InjectMocks
    ProductPortImp productPortImp;

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);

    }


    @Test
    void createProduct() {
        Product x = new Product(12,"namyye","Derivados");

        when(productPersistencePort.createProduct(any(Product.class))).thenReturn(true);
        Assertions.assertEquals(true, productPortImp.createProduct(12,"sdasdsad","Derivados"));
    }

}