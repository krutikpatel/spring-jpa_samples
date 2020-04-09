package com.sampleapp.proj1.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	
	/*
	 * knote: Lazy fetch of foreign key object, we dont always want to retrieve them with our main object.
	 * 		-so add lazy fetch.
	 * 	Caution:
	 * 		-we need to keep transaction open when we do student.getPassport()
	 * 		
	 */
	@OneToOne(fetch=FetchType.LAZY)
	private Passport passport;

	/*
	 * knote: for @ManyToMany, bedefault both sides are owner of relationship, and thus, 
	 * 		-it creates 2 join tables : sudent_course and course_student 
	 * 		- @ManyToMany in general results in JoinTable, which has id/keys column of both tables
	 * 
	 * 		-FetchStrategy bydefault for @ManyToMany is LazyFetch 
	 */
	@ManyToMany
	/*
	 * knote: customizing column and table name for @ManyToMany 
	 */
	@JoinTable(name = "STUDENT_COURSE", 
		joinColumns = @JoinColumn(name = "STUDENT_ID"), 
		inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	private List<Course> courses = new ArrayList<>();

	public Student() {
	}
	
	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}
}