package edu.isistan.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Person {

    @Id
    private int passport;
    
    @Column(name="name")
    private String name;
    
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Address address;
  

    public Person(){
        //Required for reflection.
    }
    
    public Person(int passport, String name, Address address) {
        super();
        this.passport = passport;
        this.name = name;
        this.address = address;
    }

    public int getPassport() {
        return passport;
    }

    public void setDni(int passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [passport=" + passport + ", name=" + name + "]";
    }
    
}
