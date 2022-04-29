package com.kyovint.RetoUno.infraestructure.adapters.output;

import com.kyovint.RetoUno.application.ports.output.IProductPersistencePort;
import com.kyovint.RetoUno.domain.model.Product;
import com.kyovint.RetoUno.infraestructure.exceptions.AlreadyExistsException;
import com.kyovint.RetoUno.infraestructure.exceptions.ErrorMessage;
import com.kyovint.RetoUno.infraestructure.exceptions.PersistenceException;
import com.kyovint.RetoUno.infraestructure.jpa.IProductRepository;
import com.kyovint.RetoUno.infraestructure.jpa.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductPersistenceAdapter implements IProductPersistencePort {

    //Generate Dependence between actual class and Repository which instance JPA
    @Autowired
    private IProductRepository productRepository;

    //Override of create product method. Here, JPA tries instance the attributes in persistence
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
