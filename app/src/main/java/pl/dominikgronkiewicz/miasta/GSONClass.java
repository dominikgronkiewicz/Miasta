package pl.dominikgronkiewicz.miasta;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by dominik on 05.02.16.
 */
public class GSONClass {
    private static String urlStartPart = "http://maps.googleapis.com/maps/api/geocode/json?address=";
    private static String urlEndPart = "&sensor=true";
    static String charset = "UTF-8";


    public static String downloadFile(String urlCityPart) {
        String zwracanyString = "";
        try {
            String urlCityPartEncoded = URLEncoder.encode(urlCityPart, charset);
            URL url = new URL(urlStartPart+urlCityPartEncoded+urlEndPart);

            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.addRequestProperty("Accept-Language", "pl");
            InputStream result = urlConn.getInputStream();
            if (result != null) {
                BufferedReader in = new BufferedReader(new InputStreamReader(result));
                while (in.ready()) { // wypisanie zawartości
                    zwracanyString += in.readLine();
                }
                in.close();
            }
            result.close();
            urlConn.disconnect();
        } catch (IOException ioe) {
            Log.e("getFromInternet", "Could not connect to server");
        }
        return zwracanyString;
    }

    public static ArrayList<String> parseJSON(String jsonString){
        ArrayList<String> cityData = new ArrayList<String>();


        Gson gson = new Gson();

        CityClass city = gson.fromJson(jsonString, CityClass.class);
        if(city.getResults().size()==0) {
            return cityData;
        }
        for (int i = 0; i < city.getResults().get(0).getAddressComponents().size(); i++) {
            cityData.add(String.valueOf(city.getResults().get(0).getAddressComponents().get(i).getLongName()));
        }

        return cityData;
    }
}























