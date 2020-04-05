package com.sampleapp.proj1.repositories;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sampleapp.proj1.models.Coursebasic;

@Repository
@Transactional	/* 
				knote: required for repository to make changes to DB. provides rollback if something goes wrong. Whole methods gets executed in single transaction
				*/
public class CoursebasicRepository {	
	
	/*
	 * knote: JPA provides convention of "Repository" stereotype, for interacting with DB
	 * 		-And for that it provides (and we need) "EntityManager". All repos need this
	 */
	@Autowired
	EntityManager em;
	
	public Coursebasic findById(Long id){
		return em.find(Coursebasic.class, id);
	}	
		
	public void deleteById(Long id){
		Coursebasic course = findById(id);
		em.remove(course);
	}

	/*
	 * knote: standard logic for save/update method
	 * creates if dont exist, if exists it will update
	 */
	public Coursebasic save(Coursebasic course) {
		
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		
		return course;
	}
	
	/*
	 * knote: Imp methods of EntityManager
	    
	    -em.persist(course);
		Add new row to DB
		-also, till end of that method, if that object is modified, it will be saved to DB as well. hence "persist"
		
		-@Transcational
			-entire method becomes transactional.
			-so, if a method does 
				em.persist(course);
				course.setName("dsf"); ===> this line will also be updated in DB !
		
		-em.flush()
			-changes upto that point will be sent out to DB
			-otherwise i think it will wait till end of method..
			
		-em.detach(course2)
			-em will no longer track changes to course2 and will not update/persist it in DB.
			
		-em.clear()
			-this will make em clear everything it was tracking sofar in this method.
			
		-em.refresh(course1)
			-this will fetch course1 from DB, and any local changes that were made before this to course1 will be lost/overwritten.
			
	
	 */
	public void playWithEntityManager() {
		Coursebasic course1 = new Coursebasic("Web Services in 100 Steps");
		em.persist(course1);	
		
		Coursebasic course2 = new Coursebasic("Angular Js in 100 Steps");
		em.persist(course2);

		//everything will be flushed now rather than at end of method
		em.flush();

		course1.setName("Web Services in 100 Steps - Updated");
		course2.setName("Angular Js in 100 Steps - Updated");
		
		//loose local change after last flush
		em.refresh(course1);
		
		em.flush();
	}
}