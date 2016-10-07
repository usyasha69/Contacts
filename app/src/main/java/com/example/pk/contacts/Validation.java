package com.example.pk.contacts;


public class Validation {

    /**
     * This method checked email by validate.
     *
     * @return - is validate
     */
    public static boolean checkEmail(String email) {
        boolean result = true;

        if (email.contains("@")) {
            result = false;
        }

        return result;
    }

    /**
     * This method checked telephone number by validate.
     *
     * @return - is validate
     */
    public static boolean checkTelephoneNumber(String telephoneNumber) {
        boolean result = true;

        if (!telephoneNumber.startsWith("+380") || telephoneNumber.length() != 13) {
            result = false;
        }

        return result;
    }
}
