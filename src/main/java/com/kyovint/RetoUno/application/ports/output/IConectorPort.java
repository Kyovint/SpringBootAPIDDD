package com.kyovint.RetoUno.application.ports.output;

import com.kyovint.RetoUno.domain.model.Product;
import com.kyovint.RetoUno.infraestructure.dto.ProductDTO;

import java.util.List;

public interface IConectorPort {

    boolean createProduct(Product product);
    List<ProductDTO> getlistProducts();
}
