package com.kyovint.RetoUno;

import com.kyovint.RetoUno.application.ports.input.IProductPort;
import com.kyovint.RetoUno.application.ports.output.IConectorPort;
import com.kyovint.RetoUno.application.service.ProductPortImp;
import com.kyovint.RetoUno.infraestructure.adapters.input.ProductController;
import com.kyovint.RetoUno.infraestructure.adapters.output.ConectorAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication

public class RetoUnoApplication {

	public static void main(String[] args) {


		SpringApplication.run(RetoUnoApplication.class, args);

		/* #3 POST METHOD
		* 	EVENT: The Entity has been created in application layer by Services USE CASE, now, It's necessary export data
		* 			to JPA persistence. The adapter will do that. This adapter implements the JPA Folder, that folder contains
		* 			the Repository (Instance of JPA) and ModelJPA (Structure of DATABASE). These implementations for
		* 			setter all attributes of the new entity in the DB.
		* 	DEPENDENDY NULL: Here there's no dependencies, just the adapter implements for itself the JPA instance and Model with @Autowired.
		*
		* OutputPortAppLayer OutputPortName (Output Application layer) = new InstanceAndImplementPersistenceAdapter (Output Infrastructure Layer)
		* */

		IConectorPort iConectorPort= new ConectorAdapter();

		/* #2 POST METHOD
		* 	EVENT: Controller needs transport the information from infrastructure layer to application layer to execute
		*			the Use case (Create entity). So, IPort defines the create method and Imp (Service) defines that process of creation
		*			and then,the service sends the information to output port of application layer.
		* 	DEPENDENCY: Implementation Input port (Service Application layer (USE CASE)) --> Output persistence port (Application output layer)
		*
		* 	InputPort InputPortName(USE CASE) = new Service(Output Infrastructure port)
		*  */

		IProductPort productUseCase = new ProductPortImp(iConectorPort);

		/* 	#1 POST METHOD
		*	EVENT: Controller receives HTTP POST REQUEST and validates the content of the input json through DTO
		*  	DEPENDENCY: Controller (Infrastructure Input layer) --> Input port (Application input layer)
		* 				This dependency needs the instance of product port implement. that event happens in second Step
		*
		* 	Controller ControllerName = new Controller (Input Application port)
		* */

		ProductController productController = new ProductController(productUseCase, iConectorPort);
	}

}
