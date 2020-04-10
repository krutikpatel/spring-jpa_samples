package com.sampleapp.proj1.repositories.springdatajpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sampleapp.proj1.models.Course;

/*
 * knote: sample SpringData JPA repository
 * 		- we only need to specify interface. all method implementation, wiring is by method suffix which is class variable name
 * 		-SpringDataJpa provides CRUD operations on class properties
 * 			-also provides mixed queries.
 * 		-By default SpringDataJpa provides basic Crud methods and we Dont even have to specify any mthod below.
 * 		-All methods we see below is Only custom queries that we wanted.
 * 		-findByName : last suffix is how it knows what to look for, and prefix is CRUD operation
 * 
 * 		-test usage methods are in corresonding test class
 */
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {	//knote: Long here is data type of ID prop/column of course class
	List<Course> findByNameAndId(String name, Long id);

	List<Course> findByName(String name);

	List<Course> countByName(String name);

	List<Course> findByNameOrderByIdDesc(String name);

	List<Course> deleteByName(String name);

	/*
	 * knote : custom SQL query examples, JPQL, Native Query (SQL) and Named Query (defined in Entity)
	 */
	@Query("Select  c  From Course c where name like '%100 Steps'")
	List<Course> courseWith100StepsInName();

	@Query(value = "Select  *  From Course c where name like '%100 Steps'", nativeQuery = true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();

	@Query(name = "query_get_100_Step_courses")
	List<Course> courseWith100StepsInNameUsingNamedQuery();
}