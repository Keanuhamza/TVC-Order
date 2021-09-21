package com.example.ASWS.models;

import org.springframework.boot.SpringApplication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

@Entity
public class Order {
	
    private @Id @GeneratedValue Long id;
    private Long custID;
    private String custAddress;
    private Long custPhone;
	private String productName;
	private float prodPrice;
	private int quantity;
    
	//Constructors
	public Order() {}
	public Order(Long id, Long custID, String productName, int quantity) {
		this.id = id;
		this.custID = custID;
		this.productName = productName;
		this.quantity = quantity;
	}

	//Setters and Getters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustID() {
		return custID;
	}
	public void setCustID(Long custID) {
		this.custID = custID;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public Long getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(Long custPhone) {
		this.custPhone = custPhone;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(float prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//Overrides
	@java.lang.Override
	public java.lang.String toString() {
		return "Order{" +
				"id=" + id +
				", custID=" + custID +
				", custAddress='" + custAddress + '\'' +
				", custPhone=" + custPhone +
				", productName='" + productName + '\'' +
				", prodPrice=" + prodPrice +
				", quantity=" + quantity +
				'}';
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		Order order = (Order) object;
		return java.lang.Float.compare(order.prodPrice, prodPrice) == 0 && 
        quantity == order.quantity && 
        java.util.Objects.equals(id, order.id) && 
        java.util.Objects.equals(custID, order.custID) && 
        java.util.Objects.equals(custAddress, order.custAddress) && 
        java.util.Objects.equals(custPhone, order.custPhone) && 
        java.util.Objects.equals(productName, order.productName);
	}

	public int hashCode() {
		return java.util.Objects.hash(super.hashCode(), id, custID, custAddress, custPhone, productName, prodPrice, quantity);
	}
}
