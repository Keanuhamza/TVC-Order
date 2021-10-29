package com.example.Ordering.models;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

// Customer Class
@Entity
public class Customer {
	// Attributes of class fields (Parameters) are declared.
	
	private @Id @GeneratedValue Long id;
	private String companyName;
	private String address;
	private String country;
	
	@OneToOne
  	private Contact contact;
	// Default Constructor
	public Customer() {}
  
	// Parameterized Constructor
	public Customer(Long id, String companyName, String address, String country) {
		this.id = id;
		this.companyName = companyName;
		this.address = address;
		this.country = country;
	}
  
	// Accessor Methods
	public Long getId() {
        return id;
    }

	public String getCompanyName() {
		return this.companyName;
	}
  
	public String getAddress() {
		return this.address;
	}
  
	public String getCountry() {
		return this.country;
	}

	public Contact getContact() {
		return contact;
	}

	// Mutator Methods
	public void setId(Long id) {
        this.id = id;
    }

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
  
	public void setAddress(String address) {
		this.address = address;
	}
  
	public void setCountry(String country) {
	 	this.country = country;
	}

	public void setContact(Contact contact){
		this.contact = contact;
	}
	
    // Override Methods
	@java.lang.Override
	public java.lang.String toString() {
		return "Customer{" +
				"id=" + id +
				", companyName='" + companyName + '\'' +
				", address='" + address + '\'' +
				", country='" + country + '\'' +
				", contact=" + contact +
				'}';
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		Customer customer = (Customer) object;
		return java.util.Objects.equals(id, customer.id) && java.util.Objects.equals(companyName, customer.companyName) && java.util.Objects.equals(address, customer.address) && java.util.Objects.equals(country, customer.country) && java.util.Objects.equals(contact, customer.contact);
	}

	public int hashCode() {
		return java.util.Objects.hash(super.hashCode(), id, companyName, address, country, contact);
	}
}