package com.ecommerce.ecommnerce.common.config.security.securitycomponents;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Md5EncoderDecoder {
//
//    private final String password;
//
//
//    public Md5EncoderDecoder(@Value("${md5.password}") String password) {
//        this.password = password;
//    }

    public String encryptText(String clearText) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md5.update(clearText.getBytes());

        byte[] digest = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digest);

        return hash;

    }

    public boolean matchesText(String clearText,String encryptedText) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md5.update(clearText.getBytes());

        byte[] digest = md5.digest();

        String hasedText = DatatypeConverter.printHexBinary(digest);

        return hasedText.equalsIgnoreCase(encryptedText);

    }
}
