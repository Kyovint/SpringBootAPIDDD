package com.kyovint.RetoUno.domain.model;

import com.kyovint.RetoUno.domain.exceptions.CreateProductException;
import lombok.Data;

import javax.persistence.Id;


/*Data lombok expression. D in capital letter; It contains (
getter: getters methods of this class
settter: getters methods of this class
to string: to String method for each attribute
argconstructor: It generates constructor with arguments
equalsandhashcode: Generates hashCode and equals implementations. )*/

@Data
public class Product {

    //Entity's attribute definition
    @Id
    private Integer idproduct;
    private String nameproduct;
    private String typeproduct;

    //Constructor which initiates the validations process

    public Product (Integer idproduct, String nameproduct, String typeproduct){

        validateId(idproduct);
        this.idproduct = idproduct;

        validateNameLength(nameproduct);
        this.nameproduct = nameproduct;

        validateType(typeproduct);
        this.typeproduct = typeproduct;
    }


    //Validation method for ID
    public void validateId(Integer idproduct){

        int idLength = String.valueOf(idproduct).length();

        /* THE OPTIONAL CAN'T BE HERE 'COS THE DTO ALREADY VERIFIED THE PARAMETERS AND THE DATA WILL NEVER ARRIVE "NULL" TO
            APPLICATION LAYER TO EXECUTE USE CASES.

        Optional <Integer> nullIdValidate = Optional.ofNullable(idproduct);

        if(nullIdValidate.get().equals(null)){
            throw new CreateProductException("Idgrege");
        }*/

        //If the conditional is true, Calls the createProductException and instance a message in the method
        if (idLength > 3 && idLength < 1){
            throw new CreateProductException("Id length is not allowed, The maximun Id length is 3 digits");
        }

        if (idproduct < 1 && idproduct > 500){
            throw new CreateProductException("Id value is not allowed It should have a value between 1 and 500");
        }

    }


    //Validation method for products name length
    public void validateNameLength(String nameproduct){

        if(nameproduct.length() < 2 && nameproduct.length() > 50){
            throw new CreateProductException("Length of the name product is not allowed");
        }
    }

    //Validation method for product type
    public void validateType(String typeproduct){
        if(!typeproduct.equals("Renta Fija") && !typeproduct.equals("Renta Variable") && !typeproduct.equals("Derivados")){
            throw new CreateProductException("Type of the product is not allowed ");
        }
    }

}


