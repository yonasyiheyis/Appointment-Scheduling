package com.example.demo.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

public class TMSession {

	@Id
	@GeneratedValue

	private int id;

	@Column(name = "session_date")
	private LocalDate sessionDate;

	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "session_duration")
	private int duration;

	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "counselor_id")
	private Person counselor;

	@Embedded
	private Address address;

	@OneToOne(cascade =CascadeType.ALL,mappedBy="tmSession")	
	private Appointment appointment;

	@OneToMany(cascade =CascadeType.ALL,mappedBy = "tmSession")
	private List<AppointmentRequest> appointmentRequest = new ArrayList();

}
