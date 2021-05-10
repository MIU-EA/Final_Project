package com.bestofa.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Address {
	@Id
	@GeneratedValue
    private Integer id;
    private String zipcode;
    private String street;
    private String city;
    private String state;
    private String country;
	
    public Address(String zipcode, String street, String city, String state, String country) {
		super();
		this.zipcode = zipcode;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}
    
}
