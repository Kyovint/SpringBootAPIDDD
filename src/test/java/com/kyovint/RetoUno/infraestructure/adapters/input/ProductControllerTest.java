package com.kyovint.RetoUno.infraestructure.adapters.input;

import com.kyovint.RetoUno.application.ports.input.IProductPort;
import com.kyovint.RetoUno.application.ports.output.IConectorPort;
import com.kyovint.RetoUno.domain.model.Product;
import com.kyovint.RetoUno.infraestructure.dto.ProductDTO;
import com.kyovint.RetoUno.infraestructure.jpa.ProductJPA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ProductControllerTest {


    //Mocking process, defines fake attributes of class for testing process
    @Mock
    private IProductPort iproduct;
    @Mock
    private IConectorPort iConectorPort;

    //It runs the instance of the class and its attributes. (It's like class constructure)
    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void postProduct() {
        when(iproduct.createProduct(anyInt(),anyString(),anyString())).thenReturn(false);

        ProductDTO correctProductDTO = new ProductDTO();

        correctProductDTO.setIdproduct(1);
        correctProductDTO.setNameproduct("BVCC");
        correctProductDTO.setTypeproduct("Derivados");


        ResponseEntity<String> correct = new ResponseEntity<>("Invalid Product", HttpStatus.OK);
        Assertions.assertEquals(correct,productController.postProduct(correctProductDTO) );

        when(iproduct.createProduct(anyInt(),anyString(),anyString())).thenReturn(true);

        ResponseEntity<String> incorrect = new ResponseEntity<>("Product Added", HttpStatus.OK);
        Assertions.assertEquals(incorrect,productController.postProduct(correctProductDTO) );
    }


    /*
    @Test
    void getproductsByType() {

        String type = "Derivados";
        ProductJPA product = new ProductJPA(1,"prodcut11","Renta fija");

        List<ProductJPA> ListP = new ArrayList<>();

        ListP.add(product);

        when(iConectorPort.getlistProducts()).thenReturn(ListP);
        ResponseEntity<List<ProductJPA>> Correct = new ResponseEntity<>(ListP, HttpStatus.OK);
        Assertions.assertEquals(Correct,productController.getproductsByType(type));
    }


    @Test
    void getproducts() {
    }*/
}