package com.kagangunturk.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name="customer_id")
    private Integer customer_id;

    @Column(name ="customer_firstname")
    private String customer_firstname;

    @Column(name="customer_lastname")
    private String customer_lastname;

    @Column(name="customer_email")
    private String customer_email;

    @Column(name="customer_gender")
    private String customer_gender;

    @Column(name ="customer_address" )
    private String customer_address;

    @Column(name="customer_mobileno")
    private String customer_mobileno;

    @Column(name="customer_birthdate")
    private String customer_birthdate;



}
