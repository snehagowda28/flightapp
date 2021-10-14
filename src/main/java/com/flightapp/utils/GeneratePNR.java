package com.flightapp.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class GeneratePNR {
    public String randomPNR(){
        return RandomStringUtils.random(5, true, false);
    }
}
