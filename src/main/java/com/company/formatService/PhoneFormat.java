package com.company.formatService;

public class PhoneFormat {

    public String convert(String number){
        if (!number.equals("ull")) {
            number = number.replaceAll("-", "");
            number = number.replaceAll("\\+", "");
            if (!number.matches("38.*")){
                number = "38" + number;
            }
        }
        return number;
    }
}
