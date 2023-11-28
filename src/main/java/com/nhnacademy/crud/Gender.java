package com.nhnacademy.crud;

import java.util.Random;

public enum Gender {
    M,
    F;

    Gender() {

    }

    public static Gender getRandomEnum() {
        return Gender.values()[new Random().nextInt(Gender.values().length)];
    }

    public static Gender getGender(String str) {
        switch (str.toLowerCase()) {
            case "m":
                return M;
            case "f":
                return F;
        }

        throw new IllegalArgumentException("값이 올바르지 않습니다!");
    }
}
