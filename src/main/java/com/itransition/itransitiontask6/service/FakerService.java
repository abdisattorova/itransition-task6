package com.itransition.itransitiontask6.service;


// Asatbek Xalimjonov 6/9/22 3:16 PM


import com.github.javafaker.Faker;
import com.itransition.itransitiontask6.dto.FakerDto;
import com.itransition.itransitiontask6.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class FakerService {

    Random random = new Random();
    public String letters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public String cryllic = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    public List<UserDto> getGeneretedData(FakerDto fakerDto){

        if (fakerDto==null)return null;

        List<UserDto> generetedData = new ArrayList<>();

        Faker dataFaker = new Faker(
                new Locale(fakerDto.getSelectedCountry()),
                new Random(fakerDto.getSeedNumber()));

        for (int i = 0; i < 20; i++) {
            UserDto userDto = new UserDto(
                    "" + dataFaker.number().numberBetween(10000, 99999),
                    dataFaker.name().fullName(),
                    dataFaker.address().fullAddress(),
                    dataFaker.phoneNumber().phoneNumber()
            );
            errors(userDto, fakerDto.getErrorRange(),fakerDto.getSelectedCountry());
            generetedData.add(userDto);

        }

        return generetedData;
    }

    public void errors(UserDto userDto,Double error,String cyrillic){
        boolean isCyrillic=false;
        if (cyrillic.equals("ru"))isCyrillic=true;
        for (int i = 0; i < error; i++) {
            userDtoError(userDto,isCyrillic);
        }
    }

    public UserDto userDtoError(UserDto userDto,boolean isCyrillic){

        int randomNumber=(int)(Math.random()*3) + 1;
        int randomMethod=(int)(Math.random()*3) + 1;
        switch (randomNumber){
            case 1: {
                userDto.setName(
                        randomMethod==1 ?
                        add(userDto.getName(), false,isCyrillic) :
                        randomMethod==2 ?
                        change(userDto.getName()) :
                        delete(userDto.getName())
                );
                userDto.setNameError(true);
                }
                break;
            case 2:{
                userDto.setAddress(
                        randomMethod==1 ?
                        add(userDto.getAddress(), false,isCyrillic) :
                        randomMethod==2 ?
                        change(userDto.getAddress()) :
                        delete(userDto.getAddress())
                );
                userDto.setAddressError(true);

                }
                break;
            case 3:{
                userDto.setPhone(
                        randomMethod==1 ?
                        add(userDto.getPhone(), true,isCyrillic) :
                        randomMethod==2 ?
                        change(userDto.getPhone()) :
                        delete(userDto.getPhone())
                );
                userDto.setPhoneError(true);
                }
                break;
            default:
                break;
        }

        return userDto;
    }

    private String delete(String object){
        int index= (int) (Math.random() * (object.length() - 1));
        if (index==object.length()-1){
            return object.substring(0,index-2);
        }
        return object.substring(0,index)+object.substring(index+1);
    }

    private String add(String object , boolean isNumber,boolean isCyrillic){
//        String start = "<span style=\"color: red;font-weight: bold\">";
//        String end = "</span>";

        String lettersLan="";
        if(isCyrillic==true)
            lettersLan=cryllic;
        else
            lettersLan=letters;

        int index = random.nextInt(object.length());

        if (index == object.length() - 1) return isNumber ? object.concat(String.valueOf(random.nextInt(10))) :
                object.concat(String.valueOf(lettersLan.charAt(random.nextInt(lettersLan.length()-1))));
        return isNumber ? object.substring(0,index).concat(String.valueOf(random.nextInt(10)).concat(object.substring(index+1))):
                object.substring(0, index).concat(String.valueOf(lettersLan.charAt(random.nextInt(lettersLan.length()-1)))) +
                        object.substring(index + 1);
    }

    private String change(String object){
        int changeFromIndex = random.nextInt(object.length());
        int changeToIndex = random.nextInt(object.length());
        char char1 = object.charAt(changeFromIndex);
        boolean c1=false;
        char char2 = object.charAt(changeToIndex);
        boolean c2=false;

        String replace1="";

        for (int i = 0; i < object.length(); i++) {
            if (object.charAt(i)==char1 && !c2){
//                replace1+="<span style=\"color: red;font-weight: bold\">";
                replace1+=char2;
//                replace1+="</span>";
                c2=true;
            } else if (object.charAt(i)==char2 && !c1) {
//                replace1+="<span style=\"color: red;font-weight: bold\">";
                replace1+=char1;
//                replace1+="</span>";
                c1=true;
            }else{
                replace1+=object.charAt(i);
            }
        }

        return replace1;
    }



}
