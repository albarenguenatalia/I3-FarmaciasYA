package Utils;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author martingonzalez
 */
public class prueba {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        OneWayHash p = new OneWayHash();
        System.out.println( Arrays.toString(p.hashSHA256("estaesmypassord", "misalt".getBytes())));
        System.out.println(Arrays.toString(p.hashSHA256("estaesmypassord", "misalt".getBytes())).equals(Arrays.toString(p.hashSHA256("estaesmypassord", "misalt".getBytes()))));
        System.out.println(Arrays.toString(p.hashSHA256("estaesmy1passord", "misalt".getBytes())).equals(Arrays.toString(p.hashSHA256("estaesmypassord", "misalt".getBytes()))));

        String bdy = "Usted se ha registrado exitosamente a FarmaciasYA.<br>El nuevo portal de farmacias dedicado para usted.<br>Dirijase a <a href=\"localhost:8080/I3-FarmaciasYa/template/index.xhtml\">FarmaciasYA</a> para ir a su cuenta.<br><br>Muchas Gracias.<br>Equipo de FarmaciasYA\n";
        Mail.sendMail("martingon4eo@gmail.com", "Mail de Prueba", bdy);
        String[] lista = {"martingon4eo@gmail.com","cynthia.barindelli@outlook.com","giqadel93@gmail.com","jona.amigo.ucu@gmail.com","albarenguenatalia@gmail.com"}; 
        Mail.sendMail(lista, "Mail de Prueba", bdy);
    }
}