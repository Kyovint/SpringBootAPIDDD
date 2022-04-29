package com.kyovint.RetoUno.application.ports.input;

public interface IProductPort {

    /*Receives params from controller and determines if the params are correct and if the product can be created
    returns true if the product can be created else returns false*/

    boolean createProduct (Integer idproduct, String nameproduct, String typeproduct);
}
