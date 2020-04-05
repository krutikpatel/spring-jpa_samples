package com.sampleapp.proj1.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.sampleapp.proj1.Proj1Application;
import com.sampleapp.proj1.models.Coursebasic;

/*
 * knote:
 * junit runs after mysql data setup
 * also, it runs after CommandLineRunner, so whatever we do there is part of application and happens before test
 */
//@RunWith(SpringRunner.class) knote: this is not reqd seems in newer versions
@SpringBootTest(classes=Proj1Application.class)
public class CoursebasicRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CoursebasicRepository repository;
	
	@Test
	public void findById_basic() {
		Coursebasic course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}

	@Test
	@DirtiesContext	//knote: this modifies state of application. so with this, after this test Spring will automatically reset data as it was !
	public void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	@DirtiesContext
	public void save_basic() {

		// get a course
		Coursebasic course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());

		// update details
		course.setName("JPA in 50 Steps - Updated");

		repository.save(course);

		// check the value
		Coursebasic course1 = repository.findById(10001L);
		assertEquals("JPA in 50 Steps - Updated", course1.getName());
	}
}