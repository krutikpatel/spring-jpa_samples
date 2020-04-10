package com.sampleapp.proj1.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sampleapp.proj1.models.Course;
import com.sampleapp.proj1.models.Review;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {

		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}

		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		Course course1 = new Course("Web Services in 100 Steps");
		em.persist(course1);
		
		Course course2 = findById(10001L);
		
		course2.setName("JPA in 50 Steps - Updated");	
	}
	
	/*
	 * knote: sample method to see add/insert operations for OneToMany, ManyToOne
	 */
	public void addHardcodedReviewsForCourse_Example() {
		//get the course 10003
		Course course = findById(10003L);
		logger.info("course.getReviews() -> {}", course.getReviews());
		
		//add 2 reviews to it
		Review review1 = new Review(Review.Rating.FIVE, "Great Hands-on Stuff.");	
		Review review2 = new Review(Review.Rating.FIVE, "Hatsoff.");
		
		/* knote: since this is bidirectional relationsship and we need to set data for both sides
		 * setting the relationship
		 */
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		//save it to the database
		em.persist(review1);
		em.persist(review2);
	}
	
	/*
	 * knote: OneToMany insert example
	 */
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {		
		Course course = findById(courseId);
		logger.info("course.getReviews() -> {}", course.getReviews());
		for(Review review:reviews)
		{			
			//setting the relationship
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}
}