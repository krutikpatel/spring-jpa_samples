package com.sampleapp.proj1.repositories;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleapp.proj1.Proj1Application;
import com.sampleapp.proj1.models.Student;

/*
 * knote: Autowired did not work in Spring test without : @RunWith(SpringRunner.class) 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Proj1Application.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;

	//Session & Session Factory

	
	//EntityManager & Persistence Context
	//Transaction
	
	@Test
	public void someTest() {
		repository.someOperationToUnderstandPersistenceContext();
	}


	@Test
	@Transactional
	/*
	 * knote: because we have LazyFetch and we want to make next query, we made this method Transactional
	 * 			-Test for OneToMany/ManyToOne
	 */
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}",student.getPassport());
		//assert.
	}
	
	@Test
	@Transactional
	/*
	 * knote: because we have LazyFetch and we want to make next query, we made this method Transactional
	 * 			-Test for ManyToMany
	 */
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		
		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());
	}
}