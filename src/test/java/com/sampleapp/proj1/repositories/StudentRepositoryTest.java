package com.sampleapp.proj1.repositories;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED) 
	/*
	 * knote : way to specify isolation level for transaction.
	 * 		- imp : isolation levels are ONLY AVAILALBE FOR "SPRING TRANSACTIONS" NOT JPA TRASACTIONS !
	 * 		
	 * 		-Also, JPA transactions are good if want to just talk to one type of DB (in method)
	 * 			-but if we also have steps to talk to MQ, JMX etc, Spring Transaction covers all
	 */
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