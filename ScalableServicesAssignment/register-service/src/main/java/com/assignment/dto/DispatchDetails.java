package com.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class DispatchDetails {

    private long userId;
    private String address;
    private String email;
    private String phone;

    public DispatchDetails(@JsonProperty long userId,
                           @JsonProperty String address,
                           @JsonProperty String email,
                           @JsonProperty String phone) {
        this.userId = userId;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
