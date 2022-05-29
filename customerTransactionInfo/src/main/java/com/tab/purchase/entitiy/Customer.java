package com.tab.purchase.entitiy;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data  @NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="CUSTOMER")
public class Customer {
    @Id
    @Column(name="CUSTOMER_ID")
    private Long customerID;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="EMAIL")
    private String email;
    @Column(name="AGE")
    private String age;
    @Column(name="LOCATION")
    private String location;
    }
