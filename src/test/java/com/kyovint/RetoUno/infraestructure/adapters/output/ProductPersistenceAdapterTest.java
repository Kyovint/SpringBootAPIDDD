package com.kyovint.RetoUno.infraestructure.adapters.output;

import com.kyovint.RetoUno.domain.model.Product;
import com.kyovint.RetoUno.infraestructure.jpa.IProductRepository;
import com.kyovint.RetoUno.infraestructure.jpa.ProductJPA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.asserts.Assertion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ProductPersistenceAdapterTest {

    @InjectMocks
    private ProductPersistenceAdapter productPersistenceAdapter;

    @Mock
    IProductRepository productRepository;

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {

        Product product = new Product(12,"asas","Derivados");

        when(productRepository.save(any(ProductJPA.class))).thenReturn(new ProductJPA(product.getIdproduct(),product.getNameproduct(),product.getTypeproduct()));

        Assertions.assertEquals(true, productPersistenceAdapter.createProduct(product));

    }
}