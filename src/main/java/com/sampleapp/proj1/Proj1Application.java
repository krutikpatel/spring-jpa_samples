package com.sampleapp.proj1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sampleapp.proj1.models.Course;
import com.sampleapp.proj1.repositories.CourseRepository;

/*
 * knote:
 * -here we are making this class itself implement CommandLineRunner
 * -rather than manually creating entity objects, we would create : /src/main/resources/data.sql
 * 		-and all all our initial SQL statements there
 */
@SpringBootApplication
public class Proj1Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(Proj1Application.class, args);
	}

	@Autowired
	private CourseRepository repository;

	@Override
	public void run(String... arg0) throws Exception {
		Course course = repository.findById(10001L);
		
		logger.info("Course 10001 -> {}", course);
		
		//
		//repository.deleteById(10001L);
		//
		//repository.save(new Course("Microservices in 100 Steps"));
		
		//repository.playWithEntityManager();
	}
}
