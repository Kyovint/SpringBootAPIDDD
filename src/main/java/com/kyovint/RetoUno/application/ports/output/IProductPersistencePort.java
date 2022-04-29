package com.kyovint.RetoUno.application.ports.output;

import com.kyovint.RetoUno.domain.model.Product;

public interface IProductPersistencePort {

    boolean createProduct(Product product);
}
