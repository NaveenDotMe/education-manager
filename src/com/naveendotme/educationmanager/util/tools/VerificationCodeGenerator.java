package com.naveendotme.educationmanager.util.tools;

import java.util.Random;

public class VerificationCodeGenerator {
    private final String NUMBERS = "0123456789";

    public int getCode(int length){
        StringBuilder sb = new StringBuilder();
        for (int i =0 ; i <length ; i++){
            char selectedNum = NUMBERS.charAt(new Random().nextInt(10));
            if (i==0 && (48==(int) selectedNum)){
                selectedNum=NUMBERS.charAt(new Random().nextInt(10-1+1)+1);
            }
            sb.append(selectedNum);
        }
        return Integer.parseInt(sb.toString());
    }
}
