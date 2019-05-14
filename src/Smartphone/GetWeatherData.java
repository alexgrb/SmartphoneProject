package Smartphone;
import tools.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class GetWeatherData extends JPanel {

    String icon = "01d";
    String description = "";

    public GetWeatherData(String city) {
        //setLayout(new GridLayout(3,3));
        setLayout(null);
        ///////////////////////////////
        String API_KEY = "bca5b91d81e883aa7988a8ff953ea56e"; //API gratuite obtenu sur OpenWeather
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = read.readLine()) != null) {
                result.append(line);
            }
            read.close();
            System.out.println(result);
        ////////////////////////////////

            ObjectMapper objectMapper = new ObjectMapper();
            //Le code suivant permet de rentrer en profondeur dans l'arbre JSON
            try {
                JsonNode node = objectMapper.readTree(result.toString());
                for (JsonNode item : node.get("weather")) {
                    icon = item.get("icon").textValue();
                    //icon = "src\\pictures\\" + item.get("icon").textValue() + ".png";
                    description = item.get("description").textValue();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            JLabel descriptionLabel = new JLabel(description);

            ImageIcon weatherIcon = new ImageIcon(icon);
            //Ce code permet de récupérer les données en surface du JSON
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

            JLabel currentTemp = new JLabel(mainMap.get("temp") + "C°");
            JLabel currentHumidity = new JLabel(mainMap.get("humidity") + "% taux d'humidité");
            JLabel windSpeed = new JLabel(windMap.get("speed") + " km/h");
            JLabel windAngle = new JLabel(windMap.get("deg") + "°");
            JLabel cityLabel = new JLabel(city);
           // JLabel image = new JLabel(weatherIcon);

            JLabel currentTempText = new JLabel("Current temp : ");
            JLabel descriptionText = new JLabel("likely : ");
            JLabel whiteSpace = new JLabel("");
            JLabel windText = new JLabel("Wind speed : ");


            int scaledWidth = 100;
            int scaledHeight = 100;
            ImageResizer.resize("src\\pictures\\" + icon+ ".png", "src\\pictures\\" + icon+ "2.png", scaledWidth, scaledHeight);
            JLabel image = new imageLabel(icon+"2");
            image.setBounds(0,0,100,100);
            add(image);
            descriptionText.setBounds(110, 0,150,50);
            add(descriptionText);

            descriptionLabel.setBounds(200,0,150,50);
            System.out.println(description);
            add(descriptionLabel);

            currentTemp.setBounds(10,65,50,50);
            add(currentTemp);

            windSpeed.setBounds(110, 65, 150,50);
            add(windSpeed);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        return map;
    }
}



