package com.example.Ordering.models;

import java.util.Objects;

import javax.persistence.*;

// Product Class
@Entity
public class Product {
	// Attributes of class fields (Parameters) are declared.
	private @Id @GeneratedValue Long id;
	private String productCategory;
	private String name;
	private float price;
	private int stockQuantity;

	@OneToOne
  	private ProductDetail productDetail;
	
	// Default Constructor
	public Product() {}
	
	// Parameterized Constructor
	public Product(Long id, String productCategory, String name, float price, int stockQuantity) {
		this.id = id;
		this.productCategory = productCategory;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	  
	// Accessor Methods

	public Long getId() {
        return id;
    }
	  
	public String getProductCategory() {
		return this.productCategory;
	}
	  
	public String getName() {
		return this.name;
	}
	  
	public float getPrice() {
		return this.price;
	}
	
	public int getStockQuantity() {
		return this.stockQuantity;
	}

	public ProductDetail geProductDetail() {
		return productDetail;
	}
	  
	// Mutator Methods

	public void setId(Long id) {
        this.id = id;
    }
	  
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	  
	public void setName(String name) {
		this.name = name;
	}
	  
	public void setPrice(float price) {
		 this.price = price;
	}
	
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
		
	// Override Methods
		
	@Override
	public java.lang.String toString() {
		return "Product{" +
					"ID: " + id +
	                ", Product Category=" + productCategory +
	                ", Name='" + name + '\'' +
	                ", Price=" + price + 
					", Stock Quantity=" + stockQuantity +
					", ProductDetail=" + productDetail +
	                '}';
	}
	    
	@Override
	public int hashCode() {
		return java.util.Objects.hash(super.hashCode(), id, productCategory, name, price, stockQuantity, productDetail);
	}	
}