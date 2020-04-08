package com.sampleapp.proj1.repositories;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sampleapp.proj1.models.Passport;
import com.sampleapp.proj1.models.Student;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {

		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}

		return student;
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);

		Student student = new Student("Mike");

		student.setPassport(passport);
		em.persist(student);	
	}
	
	/*
	 * knote: Transaction, PersistenceContext understanding
	 * 	-Transaction and Session are interchangeable terms used.
	 * 	-PersistenceContext is context for that transaction.
	 * 	-DB query is ONLY made at "end of transaction"
	 * 		-If method is not marked @Transactional, each call is its own transaction and PersistenceContext too.
	 */
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		//Persistence Context (student)
		
		
		//Database Operation 2 - Retrieve passport
		/*
		 * knote: if we did not have "Lazy Fetch", we would get exception here, ALso if method is not @Transactional, we will get exception.
		 * 		-conclusion: lazyFetch need same Transaction as parent class.
		 */
		Passport passport = student.getPassport();
		//Persistence Context (student, passport)

		//Database Operation 3 - update passport
		passport.setNumber("E123457");
		//Persistence Context (student, passport++)
		
		//Database Operation 4 - update student
		student.setName("Ranga - updated");
		//Persistence Context (student++ , passport++) , now everything will be saved to DB here at end of method
	}

}