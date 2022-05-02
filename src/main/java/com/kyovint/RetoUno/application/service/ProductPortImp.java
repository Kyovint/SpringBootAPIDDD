package com.kyovint.RetoUno.application.service;

import com.kyovint.RetoUno.application.ports.input.IProductPort;
import com.kyovint.RetoUno.application.ports.output.IConectorPort;
import com.kyovint.RetoUno.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductPortImp implements IProductPort {

    private IConectorPort conectorPort;


    //Dependency Injection to persistence
    @Autowired
    public ProductPortImp (IConectorPort conectorPort){
        this.conectorPort = conectorPort;
    }


    //USE CASE: create entity
    @Override
    public boolean createProduct (Integer idproduct, String nameproduct, String typeproduct){
        Optional <Product> OptionalProduct = Optional.ofNullable(new Product(idproduct, nameproduct, typeproduct));
        Product product = OptionalProduct.orElseThrow(IllegalArgumentException::new);
        //Product product = new Product(idproduct,nameproduct,typeproduct);
        return conectorPort.createProduct(product);
    }
}
