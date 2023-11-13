package com.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DispatchDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dispatchId;
    private long userId;
    private String address;
    private String email;
    private String phone;
}
