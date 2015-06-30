package com.agami.leavemanagement.helper;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class CrypticHelper
{
 SecureRandom securerandom;
 // SHA-1 is Used to generate the hash No Salt is required
 public String getHash(String plainText) throws NoSuchAlgorithmException,
   UnsupportedEncodingException
 {
  MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
  messageDigest.reset();
  return getBase64FromByte(messageDigest.digest(plainText.getBytes("UTF-8")));
 }

 // SHA-1 is Used to generate the hash Salt is required
 public String getHash(String plainText, String salt)
   throws NoSuchAlgorithmException, UnsupportedEncodingException
 {
  MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
  messageDigest.reset();
  messageDigest.update(getByteFromBase64(salt));
  return getBase64FromByte(messageDigest.digest(plainText.getBytes("UTF-8")));
 }

 // Generate Secure salt
 public String getSecureSalt() throws NoSuchAlgorithmException,
   NoSuchProviderException
 {
  securerandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
  byte[] salt = new byte[16];
  securerandom.nextBytes(salt);
  return getBase64FromByte(salt);
 }
 
 public String generateRandomPassword()
 {
  SecureRandom secureRandom = new SecureRandom();
  return new BigInteger(130,secureRandom).toString();
 }
 
 public String getBase64FromByte(byte[] data)
 {
  return org.apache.commons.codec.binary.Base64.encodeBase64String(data);
 }
 
 public byte[] getByteFromBase64(String inputValue)
 {
  return org.apache.commons.codec.binary.Base64.decodeBase64(inputValue.getBytes());
 }
}