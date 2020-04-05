package com.sampleapp.proj1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	/*
	 * knote: JPA requires us to add no arg constructor so that it can create beans. protected so that others cannot access this ctr
	 */
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
}
