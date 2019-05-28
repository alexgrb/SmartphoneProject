package Smartphone;
import sun.util.calendar.LocalGregorianCalendar;
import tools.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetWeatherData extends JPanel {

    String icon = "01d";
    String description = "";

    public GetWeatherData(String city) {

        setLayout(null);

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

            //Ce code permet de récupérer les données en surface du JSON
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

            String currentTempT = mainMap.get("temp").toString().substring(0,2) +"C°";
            JLabel currentTemp = new JLabel(currentTempT);
            String currentHumidityT = mainMap.get("humidity").toString().substring(0,2)+"%";
            JLabel currentHumidity = new JLabel( currentHumidityT);
            JLabel windSpeed = new JLabel(windMap.get("speed") + " km/h");

            int scaledWidth = 150;
            int scaledHeight = 150;
            ImageResizer.resize("src\\pictures\\" + icon+ ".png", "src\\pictures\\" + icon+ "2.png", scaledWidth, scaledHeight);
            JLabel image = new imageLabel(icon+"2");

            ImageResizer.resize("src\\pictures\\iconHumidity.png", "src\\pictures\\iconHumidity.png", 70, 70);
            JLabel imageHumidity = new imageLabel("iconHumidity");

            descriptionLabel.setBounds(40,330,150,50);
            new textResizer(descriptionLabel);
            add(descriptionLabel);

            currentTemp.setBounds(360,350,150,200);
            new textResizer(currentTemp,35);
            add(currentTemp);

            windSpeed.setBounds(45, 360, 150,200);
            new textResizer(windSpeed,35);
            add(windSpeed);

            imageHumidity.setBounds(265,485,70,70);
            add(imageHumidity);

            currentHumidity.setBounds(355, 500, 70,50);
            new textResizer(currentHumidity, 35);
            add(currentHumidity);

            String backPath = "";
            image.setBounds(330,290,scaledWidth,scaledHeight);
            add(image);
            JLabel backGround = new imageLabel("weatherBack");
            backGround.setBounds(0,275,480,500);
            add(backGround);

            switch (icon){
                case "01d" :
                    backPath = "sunny2";
                    break;
                case "02d" :
                    backPath = "fewClouds";
                    break;
                case "03d" :
                    backPath = "cloud";
                    break;
                case "04d" :
                    backPath = "cold";
                    break;
                default:
                    backPath = "rain";
                    break;
            }
            ImageIcon imgBack = new ImageIcon("src\\pictures\\"+backPath+".gif");
            JLabel back = new JLabel(imgBack);

            back.setBounds(0,10,480,275);
            add(back);

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



