package com.maxi.urlshortener.util;

public class Base62Encoder {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();

    private Base62Encoder() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada");
    }

    public static String encode(long number) {
        if (number == 0) {
            return "000000"; // caso especial
        }

        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int remainder = (int) (number % BASE);
            sb.append(ALPHABET.charAt(remainder));
            number /= BASE;
        }

        // invertir porque el append va en orden inverso
        sb.reverse();

        // padding hasta 6 caracteres
        while (sb.length() < 6) {
            sb.insert(0, '0');
        }

        return sb.toString();
    }
}
