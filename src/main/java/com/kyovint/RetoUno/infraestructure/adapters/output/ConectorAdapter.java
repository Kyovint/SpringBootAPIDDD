package com.kyovint.RetoUno.infraestructure.adapters.output;


import com.kyovint.RetoUno.application.ports.output.IConectorPort;
import com.kyovint.RetoUno.infraestructure.exceptions.PersistenceException;
import com.kyovint.RetoUno.infraestructure.jpa.IProductRepository;
import com.kyovint.RetoUno.infraestructure.jpa.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConectorAdapter implements IConectorPort {

    @Autowired
    private IProductRepository productRepository;


    @Override
    public List<ProductJPA> getlistProducts(){

        try{
            return (List<ProductJPA>) productRepository.findAll();
        }catch (Exception e ){
            throw new PersistenceException(e);
        }

    }

}
