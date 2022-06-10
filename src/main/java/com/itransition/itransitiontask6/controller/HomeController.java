package com.itransition.itransitiontask6.controller;


// Asatbek Xalimjonov 6/9/22 11:04 AM

import com.itransition.itransitiontask6.dto.FakerDto;
import com.itransition.itransitiontask6.dto.UserDto;
import com.itransition.itransitiontask6.service.FakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final FakerService fakerService;

    @GetMapping
    public String getHomePage(Model model,
                              @RequestParam(required = false) String selectedCountry,
                              @RequestParam(required = false) String errorRange,
                              @RequestParam(required = false) Integer seedNumber) {

        List<UserDto> generetedData = null;

        if (selectedCountry!=null && errorRange!=null && seedNumber !=null){
            Double error = Double.parseDouble(errorRange);
            FakerDto fakerDto = new FakerDto(selectedCountry, error, seedNumber);
            generetedData = fakerService.getGeneretedData(fakerDto);
        }

        model.addAttribute("selectedCountry",selectedCountry);
        model.addAttribute("errorRange",errorRange);
        model.addAttribute("seedNumber",seedNumber);
        model.addAttribute("fakerDataList",generetedData);
        model.addAttribute("fakerDto",new FakerDto());
        return "home";
    }







}
