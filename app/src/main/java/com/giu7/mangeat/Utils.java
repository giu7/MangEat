package com.giu7.mangeat;

public class Utils {

//            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    final public static int PASSWORD_LENGTH = 6;
    final public static String PACKAGE_NAME = "com.giu7.mangeat";
    final public static int MIN_PANINI = 0;
    final public static int MAX_PANINI = 10;

    public static boolean checkMail(String mail){
        if (mail == null)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    public static boolean checkPassword(String password){
        if (password == null)
            return false;
        if (password.length()<PASSWORD_LENGTH)
            return false;
        if (password.equals(password.toLowerCase()))
            return false;
        if (password.equals(password.toUpperCase()))
            return false;
        if (!(password.contains("0")||password.contains("1")||password.contains("2")||password.contains("3")||password.contains("4")||
                password.contains("5")||password.contains("6")||password.contains("7")||password.contains("8")||password.contains("9")))
            return false;

        return true;
    }
}
