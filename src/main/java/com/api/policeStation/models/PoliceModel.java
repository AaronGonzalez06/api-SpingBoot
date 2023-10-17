package com.api.policeStation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "police")
public class PoliceModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private Integer phone;
	
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "private_office")
	private String privateOffice;
	
	/*@Column(name = "id_supervisor")
	private Integer idSupervisor;*/
	
	 @ManyToOne
	 @JoinColumn(name = "id_supervisor")
	 private PoliceModel supervisor;
	
	 @Transient
	 private Integer munSupervisor;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPrivateOffice() {
		return privateOffice;
	}

	public void setPrivateOffice(String privateOffice) {
		this.privateOffice = privateOffice;
	}

	/*public Integer getIdSupervisor() {
		return idSupervisor;
	}

	public void setIdSupervisor(Integer idSupervisor) {
		this.idSupervisor = idSupervisor;
	}*/
	
	public PoliceModel getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(PoliceModel supervisor) {
		this.supervisor = supervisor;
	}
	
	public Integer getMunSupervisor() {
		return munSupervisor;
	}

	public void setMunSupervisor(Integer munSupervisor) {
		this.munSupervisor = munSupervisor;
	}
}
