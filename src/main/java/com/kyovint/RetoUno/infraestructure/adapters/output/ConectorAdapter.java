package com.kyovint.RetoUno.infraestructure.adapters.output;


import com.kyovint.RetoUno.application.ports.output.IConectorPort;
import com.kyovint.RetoUno.infraestructure.dto.ProductDTO;
import com.kyovint.RetoUno.infraestructure.exceptions.PersistenceException;
import com.kyovint.RetoUno.infraestructure.jpa.IProductRepository;
import com.kyovint.RetoUno.infraestructure.jpa.ProductJPA;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import com.kyovint.RetoUno.domain.model.Product;
import com.kyovint.RetoUno.infraestructure.exceptions.AlreadyExistsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ConectorAdapter implements IConectorPort {

    @Autowired
    private IProductRepository productRepository;


    @Override
    public List<ProductDTO> getlistProducts(){
        try{
            List<ProductJPA> x = productRepository.findAll();
            //List<ProductDTO> y = new ArrayList<ProductDTO>();

            List<ProductDTO> y = x.stream().map(xw -> new ProductDTO(xw.getIdproduct(), xw.getNameproduct(), xw.getTypeproduct())).collect(Collectors.toList());
            return y;
            //return (List<ProductDTO>) productRepository.findAll();
        }catch (Exception e ){
            throw new PersistenceException(e);
        }

    }

    @Override
    public boolean createProduct(Product product) {

        try{
            validIfExist(product);
            ProductJPA productJPA = new ProductJPA();
            productJPA.setIdproduct(product.getIdproduct());
            productJPA.setNameproduct(product.getNameproduct());
            productJPA.setTypeproduct(product.getTypeproduct());

            productRepository.save(productJPA);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return true;
    }

    public void validIfExist(Product product){
        if (productRepository.existsById(product.getIdproduct())){
            throw new AlreadyExistsException("User with ID: "+ product.getIdproduct()+". Already Exists");
        }
    }

}
