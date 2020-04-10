package com.sampleapp.proj1;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sampleapp.proj1.models.Coursebasic;
import com.sampleapp.proj1.models.inheritance.FullTimeEmployee;
import com.sampleapp.proj1.models.inheritance.PartTimeEmployee;
import com.sampleapp.proj1.repositories.CourseRepository;
import com.sampleapp.proj1.repositories.CoursebasicRepository;
import com.sampleapp.proj1.repositories.EmployeeRepository;
import com.sampleapp.proj1.repositories.StudentRepository;

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
	private CoursebasicRepository repository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... arg0) throws Exception {
		Coursebasic course = repository.findById(10001L);
		
		logger.info("Course 10001 -> {}", course);
		
		//
		//repository.deleteById(10001L);
		//
		//repository.save(new Course("Microservices in 100 Steps"));
		
		//repository.playWithEntityManager();
		
		//studentRepository.saveStudentWithPassport();
		
		/*
		//OneToMany
		List<Review> reviews = new ArrayList<>();
		
		reviews.add(new Review("5", "Great Hands-on Stuff."));	
		reviews.add(new Review("5", "Hatsoff."));

		courseRepository.addReviewsForCourse(10003L, reviews );
		
		Course c = courseRepository.findById(10003L);
		logger.info("************"+c.getName());
		logger.info("************"+c.getReviews().size());
		*/
		
		/*
		 * inheritence example
		 */
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));

		logger.info("All Employees -> {}", employeeRepository.retrieveAllEmployees());
	}
	
}
