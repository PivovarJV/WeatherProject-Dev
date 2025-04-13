package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {

    public static String hashPassword(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt(12));
    }

    public static Boolean isPasswordValid(String rewPassword, String hashPassword) {
        return BCrypt.checkpw(rewPassword, hashPassword);
    }
}
