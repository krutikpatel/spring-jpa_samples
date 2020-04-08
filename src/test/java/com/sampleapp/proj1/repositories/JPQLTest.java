package com.sampleapp.proj1.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleapp.proj1.Proj1Application;
import com.sampleapp.proj1.models.Coursebasic;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Proj1Application.class)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		Query query = em.createQuery("Select  c  From Course c");
		List resultList = query.getResultList();
		logger.info("Select  c  From Course c -> {}",resultList);
	}

	@Test
	public void jpql_typed() {
		TypedQuery<Coursebasic> query = 
					em.createQuery("Select  c  From Course c", Coursebasic.class);
		
		List<Coursebasic> resultList = query.getResultList();
		
		logger.info("Select  c  From Course c -> {}",resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Coursebasic> query = 
					em.createQuery("Select  c  From Course c where name like '%100 Steps'", Coursebasic.class);
		
		List<Coursebasic> resultList = query.getResultList();
		
		logger.info("Select  c  From Course c where name like '%100 Steps'-> {}",resultList);
		//[Course[Web Services in 100 Steps], Course[Spring Boot in 100 Steps]]
	}

}