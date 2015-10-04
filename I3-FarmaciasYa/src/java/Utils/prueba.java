package Utils;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author martingonzalez
 */
public class prueba {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Password p = new Password();
        System.out.println( Arrays.toString(p.getHash("estaesmypassord", "misalt".getBytes())));
        System.out.println(Arrays.toString(p.getHash("estaesmypassord", "misalt".getBytes())).equals(Arrays.toString(p.getHash("estaesmypassord", "misalt".getBytes()))));
        System.out.println(Arrays.toString(p.getHash("estaesmy1passord", "misalt".getBytes())).equals(Arrays.toString(p.getHash("estaesmypassord", "misalt".getBytes()))));

        Mail.sendMail("martingon4eo@gmail.com", "Mail de Prueba", "Hola! Esto es un mail de prueba del obligatorio de IS3");
        String[] lista = {"martingon4eo@gmail.com","cynthia.barindelli@outlook.com","giqadel93@gmail.com","jona.amigo.ucu@gmail.com","albarenguenatalia@gmail.com"}; 
        Mail.sendMail(lista, "Mail de Prueba", "Hola! Esto es un mail de prueba del obligatorio de IS3");
    }
}