 package Utils;

import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author martingonzalez
 */
public class Nominatim {
    
    public static String BASE_URL = "http://nominatim.openstreetmap.org/search/?q=%s&format=json";
    
    public String getJson(String direccion) throws MalformedURLException, IOException{
        String args = URLEncoder.encode(direccion, "UTF-8");
        String url = String.format(BASE_URL, args);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    
    public<R> R GetJsonObject(String json, Class<R> clazz){
        try{
            return new GsonBuilder().create().fromJson(json, clazz);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public Coord getCoords(String direccion){
        try {
            String json = this.getJson(direccion);
            Json[] jsonObject = GetJsonObject(json, Json[].class);
            return new Coord(Double.parseDouble(jsonObject[0].getLat()), Double.parseDouble(jsonObject[0].getLon()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return new Coord(0,0);
    }
}
