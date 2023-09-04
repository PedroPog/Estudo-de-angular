package com.api.crudangular;

import com.api.crudangular.repository.CourseRepository;
import com.api.crudangular.model.Course;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CrudAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAngularApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			Course c = new Course();
			Course d = new Course();
			c.setNome("Angular com Spring");
			d.setNome("Angular com Postgres");
			c.setCategoria("Front-end");
			d.setCategoria("Back-end");
			courseRepository.save(c);
			courseRepository.save(d);

		};
	}

}
