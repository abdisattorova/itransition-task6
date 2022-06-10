package com.itransition.itransitiontask6.dto;




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
