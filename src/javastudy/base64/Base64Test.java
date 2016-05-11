/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastudy.base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Base64;

/**
 *
 * @author admin
 */
public class Base64Test {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        String ss = "石永强";
        String asB64 = Base64.getEncoder().encodeToString(ss.getBytes("utf-8"));
        System.out.println(asB64);
        
        byte[] asBytes = Base64.getDecoder().decode(asB64);
        System.out.println(new String(asBytes,"utf-8"));
        
        Instant startTime = Instant.now();
        String imgFile = "d:/x.jpg";
        
        String imgB64 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(imgFile)));
        
        System.out.println(imgB64);
        
        byte[] imgBytes = Base64.getDecoder().decode(imgB64);
        String imgNFile = "d:/y.jpg";
        
        Files.write(Paths.get(imgNFile), imgBytes);
        Instant endTime = Instant.now();
        
        System.out.println((endTime.toEpochMilli()-startTime.toEpochMilli())/1000.0);
    }

    
}
