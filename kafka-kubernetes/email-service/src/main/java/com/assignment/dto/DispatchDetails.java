package com.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispatchDetails {

    private long dispatchId;
    private long userId;
    private String address;
    private String email;
    private String phone;
}
