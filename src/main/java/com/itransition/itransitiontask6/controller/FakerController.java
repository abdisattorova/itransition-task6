package com.itransition.itransitiontask6.controller;


// Asatbek Xalimjonov 6/9/22 4:45 PM


import com.itransition.itransitiontask6.dto.FakerDto;
import com.itransition.itransitiontask6.dto.UserDto;
import com.itransition.itransitiontask6.service.FakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FakerController {

    private final FakerService fakerService;

    @PostMapping("/generate/{dataLength}")
    public List<UserDto> getFakerData(@RequestBody FakerDto fakerDto, @PathVariable Integer dataLength){
        return fakerService.getGeneretedData(fakerDto,dataLength);
    }

}

