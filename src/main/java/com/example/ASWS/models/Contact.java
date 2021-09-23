package com.example.ASWS.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contact {

    private @Id @GeneratedValue Long id;
    private String name;
    private Long phone;
    private String email;
    private String position;

    //Constructors
    public Contact() {}

    public Contact(Long id, String name, Long phone, String email, String position) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }
    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    //Overides
    @Override
    public java.lang.String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email=" + email +
                ", position=" + position +
                '}';
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Contact contact = (Contact) object;
        return phone == contact.phone && java.util.Objects.equals(id, contact.id) && java.util.Objects.equals(name, contact.name) && java.util.Objects.equals(email, contact.email) && java.util.Objects.equals(position, contact.position);
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, name, phone, email, position);
    }

}
