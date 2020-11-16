package com.theedal.restapi.samplecrud.utils;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

public class CustomUtils {

    public final String generateToken() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update((dateFormat.format(date).toString() + String.valueOf(Math.random())).getBytes());
        byte[] digiest = messageDigest.digest();
        String token = "";
        token = DatatypeConverter.printHexBinary(digiest);
        return token;
    }
}