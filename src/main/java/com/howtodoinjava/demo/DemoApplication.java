package com.howtodoinjava.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;

@SpringBootApplication
public class DemoApplication  extends SpringBootServletInitializer implements CommandLineRunner{

	@Autowired
	private EmployeeService service;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Application démarrée");
	}
	
//	@Override
//	protected DemoApplication configure(DemoApplication application) {
//		return application.sources(DemoApplication.class);
//	}


	@Override
	/**
	 * vararg:
	 */
	public void run(String... args) throws Exception {
		service.deleteAll();
		service.save(new EmployeeEntity("name1", "name1", "Technicien@gmail.com"));
		service.save(new EmployeeEntity("name2", "name2", "Technicien@gmail.com"));
		service.save(new EmployeeEntity("name3", "name3", "Chauffeur@gmail.com"));
		service.save(new EmployeeEntity("name4", "name4", "Comptable@gmail.com"));
		service.save(new EmployeeEntity("name5", "name5", "Comptable@gmail.com"));
		service.save(new EmployeeEntity("name6", "name6", "Chefdeprojet@gmail.com"));
		service.save(new EmployeeEntity("name7", "name7", "Responsableduservice@gmail.com"));
		service.save(new EmployeeEntity("name8", "name8", "Comptable@gmail.com"));
	}

}
