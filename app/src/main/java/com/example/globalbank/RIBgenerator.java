package com.example.globalbank;

import java.util.Random;

public class RIBgenerator {

    public static String generateRIB(String bankCode, String cityCode) {
        Random random = new Random();
        long accountNumber = (long) (Math.random() * 9_000_000_000_000_000L) + 1_000_000_000_000_000L;
        String formattedAccountNumber = String.valueOf(accountNumber);
        String rib = bankCode + cityCode + formattedAccountNumber + "00";
        int[] weights = { 89, 15, 76, 34, 21, 43, 65, 98, 10, 84, 42, 63, 87, 27, 45, 36, 73, 52, 94, 32 };
        int sum = 0;
        for (int i = 0; i < rib.length(); i++) {
            char c = rib.charAt(i);
            int n = Character.isDigit(c) ? Character.getNumericValue(c) : (c - 'A' + 10);
            sum += n * weights[i % weights.length];
        }
        int key = 97 - (sum % 97);
        String ribWithKey = bankCode + cityCode + formattedAccountNumber + String.format("%02d", key);
        return ribWithKey;
    }





























}
