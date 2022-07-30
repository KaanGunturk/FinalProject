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
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name="payments_id")
    private Integer payments_id;

    @Column(name="customerpolicy_id")
    private Integer customerpolicy;

    @Column(name="payments_amount")
    private double payments_amount;

    @Column(name="payments_date")
    private String payments_date;





}
