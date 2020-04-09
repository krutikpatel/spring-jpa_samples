package com.sampleapp.proj1.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NamedQueries(value = { 
		@NamedQuery(name = "query_get_all_courses", 
				query = "Select  c  From Course c"),
		@NamedQuery(name = "query_get_100_Step_courses", 
		query = "Select  c  From Course c where name like '%100 Steps'") })
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	/*
	 * knote: mappedBy indicates that other side is owner of this relationship, 
	 * 			AND this side will not have "courseID" as foreign key column
	 * 			-"course" below is actual name of class variable on other side
	 * 
	 * 			-Also, generally with collection, we dont provide setter for whole collection, 
	 * 			we want others to modify elems one by one, not the whole thing
	 */
	/*
	 * knote: fetch strategy by default for OneToMany is LazyFetch
	 */
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<>();
	
	/*
	 * knote: for @ManyToMany, making other side (Student) owner of relationship, so that we only have on JoinTable (student_course)
	 * 			-but it really does not matter which side is owner, because resulting info in JoinTable is same 
	 */
	@ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<>();
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	protected Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}
}
