package com.sampleapp.proj1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}
}