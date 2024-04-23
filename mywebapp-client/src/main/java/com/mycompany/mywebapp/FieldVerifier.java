//task 5.6
package com.mycompany.mywebapp;

public class FieldVerifier {

    /**
     * Verify that the specified email address is valid.
     *
     * @param email the email address to validate
     * @return true if the email address is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        // Simple email validation regex
        return email.matches("^\\S+@\\S+\\.\\S+$");
    }

    /**
     * Verify that the specified name is valid.
     *
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(String name) {
        return name != null && name.length() >= 4;
    }
}
