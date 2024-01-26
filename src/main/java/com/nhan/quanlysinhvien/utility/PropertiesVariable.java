/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.utility;

import com.nhan.quanlysinhvien.view.LoginFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author huunh
 */
public class PropertiesVariable {

    private static Properties properties = new Properties();

    static {
        InputStream is = null;
        try {
            is = ClassLoader.getSystemResourceAsStream("login.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   public static void updateProperties(String key, String value) {
    OutputStream os = null;
    try {
        // Set the property value
        properties.setProperty(key, value);

        // Save the properties to the 'login.properties' file in the 'resources' directory
        String path = "resources/login.properties";
        os = new FileOutputStream(path);
        properties.store(os, "Updated property: " + key);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



    public static String get(String key) {
        return properties.getProperty(key);
    }
}

