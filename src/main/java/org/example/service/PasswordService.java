package org.example.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public String hashPassword(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt(12));
    }

    public Boolean checkPassword(String rewPassword, String hashPassword) {
        return BCrypt.checkpw(rewPassword, hashPassword);
    }
}
