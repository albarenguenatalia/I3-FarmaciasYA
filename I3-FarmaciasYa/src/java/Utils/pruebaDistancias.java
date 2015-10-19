package Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author martingonzalez
 */
public class pruebaDistancias {
        public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
            Coord a = new Coord(-34.702150, -55.890497);
            Coord b = new Coord(-34.908348, -56.187678);
            double distance = Distance.distance(a, b);
            System.out.println(distance);
            
            a = new Coord(-34.702150, -55.890497);
            b = new Coord(-34.723740, -55.959076);
            distance = Distance.distance(a, b);
            System.out.println(distance);
            
            a = new Coord(-34.702150, -55.890497);
            b = new Coord(-34.701586, -55.905002);
            distance = Distance.distance(a, b);
            System.out.println(distance);
            
            
            Nominatim n = new Nominatim();
            Coord c = n.getCoords("Garibaldi 2345, Montevideo");
            System.out.println(c.getLatitude());
            System.out.println(c.getLongitude());
            System.out.println("---------------------------");

        }
}
