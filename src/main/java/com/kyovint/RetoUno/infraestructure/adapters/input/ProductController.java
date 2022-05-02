package com.kyovint.RetoUno.infraestructure.adapters.input;

import com.kyovint.RetoUno.application.ports.input.IProductPort;
import com.kyovint.RetoUno.application.ports.output.IConectorPort;
import com.kyovint.RetoUno.infraestructure.dto.ProductDTO;
import com.kyovint.RetoUno.infraestructure.exceptions.AlreadyExistsException;
import com.kyovint.RetoUno.infraestructure.jpa.ProductJPA;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;


//This class receives HTTP Requests from client and instance ports and connectors

@RestController
//path param is the same as "/product"
@RequestMapping(path = "product")
@Validated
public class ProductController {

    //Input Route #1
    //Instance of product port. This port is into application layer (Input). It drives the information to business process
    private IProductPort iproduct;


    //Input Route #2
    /*Instance of connector port. This port is into application layer (Output)
    *The reason 'cos this port are instanced here is 'cos sometimes the information doesn't need
    *business process just DB insertions or something like that. It is normally use to GET method.
     */
    private IConectorPort iConectorPort;


    //Constructor and Dependency injection with IProductPort to transfer the data to application layer
    public ProductController(IProductPort iproduct, IConectorPort iConectorPort){
        this.iproduct = iproduct;
        this.iConectorPort=iConectorPort;
    }



    /*CrossOrigin refers to the origin of the information. This annotation defines who can process the information
    *and how can process that information.
    *
    *This annotation is important 'cos configures CORS for a specific method, avoiding running CORS erros
    *
    *CORS means: Cross Origin Resources Sharing. It's a way of share information between servers and clients
    *
    *In this case, the params of CrossOrigin defines:
    *Value (All users can use the method with the * value)
    *methods (Defines the HTTP methods will be used for any method, POST in this case)
    */

    @PostMapping("/create")
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<String> postProduct (@Valid @RequestBody ProductDTO productDTO) {
        String response = "";

        //Valid with DTO the content of the object
        boolean validContent = iproduct.createProduct(productDTO.getIdproduct(),productDTO.getNameproduct(),productDTO.getTypeproduct());
        if (validContent){
            response = "Product Added";
        }
        else {
            response = "Invalid Product";
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/consultAll")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public ResponseEntity<List<ProductDTO>> getproducts(){
        List<ProductDTO> productList = iConectorPort.getlistProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /* WAIT A SECOND*/

    @GetMapping("/{type}"+"Type")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public ResponseEntity<List<ProductDTO>> getproductsByType(@PathVariable String type){
        List<ProductDTO> productList = iConectorPort.getlistProducts();

        if (type.equals("")){
            throw new AlreadyExistsException("Impossible find products with empty param.");
        }
        else{
            return new ResponseEntity<>(productList
                    .stream()
                    .filter(x -> x.getTypeproduct().equals(type))
                    .collect(Collectors.toList())
                    , HttpStatus.OK);
        }

    }

    /* OPTIONAL IN PATH VALUE
    @GetMapping("/{Optionaltype}"+"Type")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public ResponseEntity<List<ProductJPA>> getproductsByType(@PathVariable Optional<String>Optionaltype){
        List<ProductJPA> productList = iConectorPort.getlistProducts();

        if (Optionaltype.get().equals("")|| !Optionaltype.get().equals("Derivados")){
            //throw new AlreadyExistsException("User with ID: fgdfgfdsfsdgsdf Already Exists");
        }
        else{
            return new ResponseEntity<>(productList
                    .stream()
                    .filter(x -> x.getTypeproduct().equals(Optionaltype.get()))
                    .collect(Collectors.toList())
                    , HttpStatus.OK);
        }
    }*/

}
