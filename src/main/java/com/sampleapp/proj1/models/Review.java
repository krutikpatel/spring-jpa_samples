package com.sampleapp.proj1.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	public enum Rating {
		ONE, TWO, THREE, FOUR, FIVE
	}
	
	@Id
	@GeneratedValue
	private Long id;

	/*
	 * knote: example of how to store enums in DB
	 * 		-all we need is this annotation,
	 * 		-EnumType.STRING : tells that store this value as String rep of this enum value
	 * 		-EnumType.ORDINAL : stores int numeric value of enum (that might change later if we add some new enum value in-between), so not good idea
	 */
	@Enumerated(EnumType.STRING)
	private Rating rating;

	private String description;

	/*
	 * knote: as a result of this, Review DB table will generate courseID column, as foreign key col
	 */
	/*
	 * knote: fetch strategy by default for @ManyToOne is always EagerFetch
	 */
	@ManyToOne
	private Course course;

	protected Review() {
	}

	public Review(Rating rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return String.format("Review[%s %s]", rating, description);
	}
}