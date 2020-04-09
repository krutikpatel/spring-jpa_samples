package com.sampleapp.proj1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;

	/*
	 * knote: bidirectional mapping, if we want.
	 * 		-mappedBy is also optional, if we dont mention, we get extra foreign key col in Passport table.
	 * 		-with mappedBy, we dont get this foreign key col here
	 * 		-that also means Student is owner of this relationship.
	 * 		-the prop name we mention inside mappedBy is the "class variable" that we want to link. here its Student.passport
	 */
	@OneToOne(fetch=FetchType.LAZY, mappedBy="passport")
	private Student student;
	
	protected Passport() {
	}

	public Passport(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Passport[%s]", number);
	}
}
