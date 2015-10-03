
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author martingonzalez
 */
public class prueba {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Password p = new Password();
        System.out.println( Arrays.toString(p.getHash("estaesmypassord", "misalt".getBytes())));
        System.out.println(Arrays.toString(p.getHash("estaesmypassord", "misalt".getBytes())).equals(Arrays.toString(p.getHash("estaesmypassord", "misalt".getBytes()))));
        System.out.println(Arrays.toString(p.getHash("estaesmy1passord", "misalt".getBytes())).equals(Arrays.toString(p.getHash("estaesmypassord", "misalt".getBytes()))));

    }
}
