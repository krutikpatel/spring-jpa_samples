package com.sampleapp.proj1.models.inheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/*
 * knote: JPA inheritance strategies:
 * 		-default is "SINGLE_TABLE", and columns not relevant for any subclass, will have null entry in table
 * 			-Table will have additional column call DTYPE : the subclass name
 * 
 * 		-Use Single Table when performance is desired
 * 		-Use Joined, when data integrety is more imp
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	protected Employee() {
	}

	public Employee(String name) {
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

	@Override
	public String toString() {
		return String.format("Employee[%s]", name);
	}
}