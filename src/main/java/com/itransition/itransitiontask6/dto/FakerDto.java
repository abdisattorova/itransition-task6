package com.itransition.itransitiontask6.dto;


// Asatbek Xalimjonov 6/9/22 3:05 PM


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FakerDto {

    private String selectedCountry;
    private Double errorRange;
    private Integer seedNumber;

}
