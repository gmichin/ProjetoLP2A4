package com.ifsp.lp.work;

import com.ifsp.lp.work.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkApplication {

	public static void main(String[] args) {
	//	UserService userService = new UserService();

		// Chame o método desejado da classe de serviço
	//	userService.combineUserLists();
		SpringApplication.run(WorkApplication.class, args);


	}

}
