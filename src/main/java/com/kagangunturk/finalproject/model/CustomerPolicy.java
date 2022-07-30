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
@Table(name = "customer_policy")
public class CustomerPolicy {

    @Id
    @Column(name="customerpolicy_id")
    private Integer customerpolicy_id;

    @Column(name="customer_id")
    private Integer customer;


    @Column(name="policy_id")
    private Integer policy;





}
