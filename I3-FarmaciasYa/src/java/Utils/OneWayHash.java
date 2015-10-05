package Utils;

 
 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;
 import java.io.UnsupportedEncodingException;
 
 public class OneWayHash {
   private static OneWayHash instance;
   
   public static OneWayHash getInstance() {
       if (instance == null){ 
           instance = new OneWayHash();
       }
       return instance;
   }

   public byte[] hashSHA256(String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
       MessageDigest digest = MessageDigest.getInstance("SHA-256");
       digest.reset();
       digest.update(salt);
       return digest.digest(password.getBytes("UTF-8"));
   }
 }