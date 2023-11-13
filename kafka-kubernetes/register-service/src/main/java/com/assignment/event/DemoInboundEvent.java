package com.assignment.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoInboundEvent {

    private String email;
    private String userName;
    private String ageCategory;
    private String address;
    private String phone;
}
