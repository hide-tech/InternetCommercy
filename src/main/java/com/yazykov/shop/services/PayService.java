package com.yazykov.shop.services;

import com.yazykov.shop.dto.CreditCardDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PayService {

    public Boolean proceed(CreditCardDto cardDto){
        Random random = new Random();
        return random.nextBoolean();
    }
}
