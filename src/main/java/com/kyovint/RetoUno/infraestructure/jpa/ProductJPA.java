package com.kyovint.RetoUno.infraestructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

//Instance of table in DB and its model

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "product")
public class ProductJPA {

    @Id
    private Integer idproduct;
    private String nameproduct;
    private String typeproduct;
}
