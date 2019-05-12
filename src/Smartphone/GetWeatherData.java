package Smartphone;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class GetWeatherData extends JPanel {
    protected JTextArea currentTemp = new JTextArea();
    protected JTextArea windSpeed = new JTextArea();
    ImageIcon h = new ImageIcon("src\\pictures\\weatherBackground.jpg");
    JLabel x = new JLabel(h);

    public GetWeatherData() {
        showData();
        this.add(x);
    }

    public static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {}.getType()
        );
        return map;



    }
    public void showData(){

        String API_KEY = "bca5b91d81e883aa7988a8ff953ea56e"; //API gratuite obtenu sur OpenWeather
        String LOCATION = "Sion,CH"; //L'id de sion
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q="+LOCATION+"&appid="+API_KEY+"&units=metric";

        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = read.readLine())!= null){
                result.append(line);
            }
            read.close();
            System.out.println(result);

            /*String mapObject = result.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(mapObject,new TypeReference<Map<String,Object>>(){});
            System.out.println(jsonMap.get("weather"));


*/
            ObjectMapper objectMapper = new ObjectMapper();
            currentTemp.append( "Ici les données météo." );

            String temp ="";
            //Le code suivant permet de rentrer en profondeur dans l'arbre JSON
            try {
                JsonNode node = objectMapper.readTree(result.toString());
                for (JsonNode item : node.get("weather")) {
                    System.out.println(item.get("icon"));
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            //Ce code permet de récupérer les données en surface du JSON
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

            System.out.println("Current temperature : " + mainMap.get("temp"));
            System.out.println("Current humidity : " + mainMap.get("humidity"));
            System.out.println("Wind Speeds : " + windMap.get("speed"));
            System.out.println("Wind Angle" + windMap.get("deg"));

            currentTemp.append("Current temperature : " + temp);

                    add(currentTemp);
            add(windSpeed);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
