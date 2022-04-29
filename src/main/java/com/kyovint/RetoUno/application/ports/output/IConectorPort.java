package com.kyovint.RetoUno.application.ports.output;

import com.kyovint.RetoUno.infraestructure.jpa.ProductJPA;

import java.util.List;

public interface IConectorPort {

    List<ProductJPA> getlistProducts();
}
