package com.example.Ordering.models;

import java.util.Objects;

import javax.persistence.*;

// ProductDetail Class
@Entity
public class ProductDetail {
	// Attributes of class fields (Parameters) are declared.
	private @Id @GeneratedValue Long id;
	private String description;
	private String comment;

	// Default Constructor
	public ProductDetail() {}
	
	// Parameterized Constructor
	public ProductDetail(Long id, String description, String comment) {
		this.id = id;
		this.description = description;
		this.comment = comment;
	}
	  
	// Accessor Methods

	public Long getId() {
        return id;
    }
	  
	public String getDescription() {
		return this.description;
	}
	  
	public String getComment() {
		return this.comment;
	}
	  
	// Mutator Methods

	public void setId(Long id) {
        this.id = id;
    }
	  
	public void setDescription(String description) {
		this.description = description;
	}
	  
	public void setComment(String comment) {
		this.comment = comment;
	}
		
	// Override Methods
		
	@Override
	public java.lang.String toString() {
		return "ProductDetail{" +
					"ID: " + id + 
	                ", Description=" + description +
	                ", Comment='" + comment + '\'' +
	                '}';
	}
	    
	@Override
	public int hashCode() {
		return java.util.Objects.hash(super.hashCode(), id,  description, comment);
	}	
}