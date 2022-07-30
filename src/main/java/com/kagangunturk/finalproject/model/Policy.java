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
@Table(name = "policy")
public class Policy {


    @Id
    @Column(name="policy_id")
    private Integer policy_id;

    @Column(name="policy_name")
    private String policy_name;

    @Column(name="policy_description")
    private String policy_description;




}
