package com.example.demo.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "user_name")
	private String username;

	@Column(name = "password")
	private String password;

	// @OneToMany(cascade =CascadeType.ALL)

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)

	private Set<RoleType> roles = new HashSet<>();
	// private Collection<RoleType> role = new ArrayList();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
	@JsonIgnore
	List<Appointment> appointments = new ArrayList<>();

	// for counselor only
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "counselor")
	@JsonIgnore
	List<TMSession> tmSessions = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
	@JsonIgnore
	List<AppointmentRequest> appointmentRequests = new ArrayList<>();

}
