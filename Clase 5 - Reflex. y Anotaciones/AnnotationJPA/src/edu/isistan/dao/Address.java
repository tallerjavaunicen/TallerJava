package edu.isistan.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(name="city")
    private String city;
    
    @Column(name="street")
    private String street;
    
    @Column(name="number")
    private int number;
    
    @OneToMany(mappedBy="address")
    private List<Person> inhabitants;
    
    public Address(){
        //Required for reflection.
    }
    
    public Address(String city, String street, int number) {
        super();
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public List<Person> getInhabitants() {
        return inhabitants;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", street=" + street + ", number=" + number + ", inhabitants=" + inhabitants
                + "]";
    }
    
}
