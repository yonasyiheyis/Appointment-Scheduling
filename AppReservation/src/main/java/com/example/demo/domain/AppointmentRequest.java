package com.example.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

public class AppointmentRequest {

	@Id
	@GeneratedValue

	private int id;

	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name ="person_id")
	private Person person;

	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name ="tmSession_id")
	private TMSession tmSession;

}
