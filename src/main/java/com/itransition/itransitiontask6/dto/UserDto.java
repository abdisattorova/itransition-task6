package com.itransition.itransitiontask6.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDto {

    private String id;
    private String name;
    private String address;
    private String phone;
    private boolean isNameError;
    private boolean isAddressError;
    private boolean isPhoneError;

    public UserDto(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
