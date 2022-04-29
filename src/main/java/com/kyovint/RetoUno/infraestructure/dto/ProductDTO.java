package com.kyovint.RetoUno.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/* DTO means: Data Transfer Object
* It's a class which permits transport data in object form between process in the application
* Here, we should define each attributes which we gonna transport for each object.
* For example. Sometimes we don't need transport the client's password, in that case, our DTO dosen't contain the password.
*  */

@Data
@NoArgsConstructor
@AllArgsConstructor

/* Serializable: It's a java.io class. It allows to generate a bytes secuence to storage or transport of the information.
* It's essential for a DTO.
* */
public class ProductDTO implements Serializable {

    @NotNull
    @NotBlank
    @Valid
    //@Column(unique=true)
    private Integer idproduct;

    @NotNull
    @NotBlank
    @Valid
    private String nameproduct;

    @NotNull
    @NotBlank
    @Valid
    private String typeproduct;

}
