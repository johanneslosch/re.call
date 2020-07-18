package com.johanneslosch.recall.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
  public static String read(String path, String filename, String key) {
    Properties prop = new Properties();
    InputStream input = null;
    String ending = "conf";
    if (FileHelper.checkFile(path, filename, ending)) {
      try {

        input = new FileInputStream(
            new File(String.format("%s/%s.%s", path, filename, ending)));

        // load a properties file
        prop.load(input);

        // get the property value and print it out
        prop.getProperty(key);

      } catch (IOException ex) {
        ex.printStackTrace();
      } finally {
        if (input != null) {
          try {
            input.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return prop.getProperty(key);
  }
}
