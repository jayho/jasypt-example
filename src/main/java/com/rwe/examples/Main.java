package com.rwe.examples;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import java.io.IOException;
import java.util.Properties;

public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("Reading via standard j.u.Properties:");
    getProperties().list(System.out);
    System.out.println("\nReading via javasypt properties:");
    getEncryptedProperties("jasypt").list(System.out);
  }

  private static Properties getProperties() throws IOException {
    // Load properties
    Properties props = new Properties();
    props.load(ClassLoader.getSystemResourceAsStream("config.properties"));
    return props;
  }

  private static Properties getEncryptedProperties(String password) throws IOException {
    // Create encryptor
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setPassword(password); // could be got from web, env variable...
    // Load properties
    Properties props = new EncryptableProperties(encryptor);
    props.load(ClassLoader.getSystemResourceAsStream("config.properties"));
    return props;
  }
}
