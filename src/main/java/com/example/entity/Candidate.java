package com.example.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String univName;

	private String email;

	private String linkedInUrl;

	private String degree;

	private String username;

	@OneToMany
	@Transient
	private List<Job> appliedJobs;


	@Transient
	private String applicationStatus;


	public Candidate(){
		
	}
	
	public Candidate(String name, String univName, String email, String linkedInUrl, String degree,
			String username) {

		this.name = name;
		this.univName = univName;
		this.email = email;
		this.linkedInUrl = linkedInUrl;
		this.degree = degree;
		this.username = username;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Job> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(List<Job> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}
	
	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getUnivName() {
		return univName;
	}

	public void setUnivName(String univName) {
		this.univName = univName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}