package com.sampleapp.proj1.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleapp.proj1.Proj1Application;
import com.sampleapp.proj1.models.Course;
import com.sampleapp.proj1.models.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Proj1Application.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}

	@Test
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}

	@Test
	@DirtiesContext
	public void save_basic() {

		// get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());

		// update details
		course.setName("JPA in 50 Steps - Updated");
		repository.save(course);

		// check the value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 Steps - Updated", course1.getName());
	}

	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

	@Test
	@Transactional
	public void testGetOneToMany() {
		List<Review> reviews = new ArrayList<>();
		
		reviews.add(new Review("5", "Great Hands-on Stuff."));	
		reviews.add(new Review("5", "Hatsoff."));

		repository.addReviewsForCourse(10003L, reviews );
		
		Course c = repository.findById(10003L);
		logger.info("************"+c.getName());
		
		/*
		 * knote: without @Transational, we will get LazyInitializationException, because we are assuming existing repo connection
		 */
		logger.info("************"+c.getReviews().size());

	}
}