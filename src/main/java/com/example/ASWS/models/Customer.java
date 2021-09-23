package com.example.ASWS.models;

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
/*
@SpringBootApplication
class FatApplication {
	public static void main(String[] args) {
		SpringApplication.run(FatApplication.class, args);
		
		// Import New Scanner Object.
		Scanner input = new Scanner(System.in);
		
		// Declaring attributes for storing the customer. 
		String companyName, address, country;
		
		// Create Customers
		// Display message asking to enter the personal details of the customer.
		System.out.println("\nEnter the following details to create customer.");
		
		// Display message for user to enter the customer company name. 
		System.out.print("Please input customers company name: ");
		companyName = input.nextLine();
		// No input from user.
		if(companyName.equals("")) {
			throw new InputMismatchException("Customers company name is not inputtted.");
        	}
		
		// Display message for user to enter the customer company name. 
		System.out.print("Please input customers address: ");
		address = input.nextLine();
		// No input from user.
		if(companyName.equals("")) {
			throw new InputMismatchException("Customers address is not inputtted.");
        	}
		
		// Display message for user to enter the customer company name. 
		System.out.print("Please input customers country: ");
		country = input.nextLine();
		// No input from user.
		if(companyName.equals("")) {
			throw new InputMismatchException("Customers country is not inputtted.");
        	}
		
		// Create customer object of the Customer class to store the information of the Customer.
		Customer customer = new Customer(companyName, address, country);
		
		// Update Customer
		
		// Create boolean object to see if they would like to update the customers details (set false as default).
		boolean updateCustomer = false;
		
		// Display message asking if they would like to update the personal details of the customer.
		System.out.println("\nWould you like to update the personal details of the customer?");
		if (updateCustomer == true) {
			System.out.println("\nSelect one of the following fields to update (Select 4 to exit when completed): " + "\n"
					          + "1. Company Name" + "\n" + "2. Address" + "\n" + "3. Country" + "\n" + "4. Exit");
			int fieldSelected = input.nextInt();
			switch(fieldSelected) {
				case 1:
					System.out.print("Please input customers company name: ");
					companyName = input.nextLine();
					// No input from user.
					if(companyName.equals("")) {
						throw new InputMismatchException("Customers company name is not inputtted.");
			        	}
					break;
				case 2:
					// Display message for user to enter the customer company name. 
					System.out.print("Please input customers address: ");
					address = input.nextLine();
					// No input from user.
					if(companyName.equals("")) {
						throw new InputMismatchException("Customers address is not inputtted.");
			        	}
					break;
				case 3:
					// Display message for user to enter the customer company name. 
					System.out.print("Please input customers country: ");
					country = input.nextLine();
					// No input from user.
					if(companyName.equals("")) {
						throw new InputMismatchException("Customers country is not inputtted.");
			        	}
					break;
				case 4:
					System.exit(0);
			}
		}
		// Close Scanner
		input.close();
	}
}
*/