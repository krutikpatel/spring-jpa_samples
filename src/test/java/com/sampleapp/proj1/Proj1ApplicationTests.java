package com.sampleapp.proj1;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sampleapp.proj1.models.Coursebasic;
import com.sampleapp.proj1.repositories.CoursebasicRepository;

@SpringBootTest
class Proj1ApplicationTests {

	@Test
	void contextLoads() {
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CoursebasicRepository repository;
	
	@Test
	public void findById_basic() {
		Coursebasic course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}

}
