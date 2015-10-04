package Utils;

 
 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;
 import java.io.UnsupportedEncodingException;
 
 public class Password {
 
   public Password() {
       
   }

   public byte[] getHash(String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
       MessageDigest digest = MessageDigest.getInstance("SHA-256");
       digest.reset();
       digest.update(salt);
       return digest.digest(password.getBytes("UTF-8"));
   }
 }