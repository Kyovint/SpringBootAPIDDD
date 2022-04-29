package com.kyovint.RetoUno.infraestructure.adapters.output;

import com.kyovint.RetoUno.infraestructure.jpa.IProductRepository;
import com.kyovint.RetoUno.infraestructure.jpa.ProductJPA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConectorAdapterTest {

    @InjectMocks
    private ConectorAdapter conectorAdapter;

    @Mock
    IProductRepository productRepository;

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getlistproducts(){

        List<ProductJPA> listProducts = new ArrayList<>();
        listProducts.add(new ProductJPA(12,"dsadasd","Derivados"));

        when(productRepository.findAll()).thenReturn(listProducts);

        Assertions.assertEquals(listProducts, conectorAdapter.getlistProducts());
    }
}