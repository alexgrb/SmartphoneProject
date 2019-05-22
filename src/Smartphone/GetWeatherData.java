package Smartphone;
import sun.util.calendar.LocalGregorianCalendar;
import tools.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.imageio.ImageIO;
import javax.swing.*;
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
            Map<String, Object> sysMap = jsonToMap(respMap.get("sys").toString());

            JLabel currentTemp = new JLabel(mainMap.get("temp") + "C°");
            JLabel currentHumidity = new JLabel( mainMap.get("humidity") +"%");
            JLabel windSpeed = new JLabel(windMap.get("speed") + " km/h");
            JLabel cityLabel = new JLabel(city);

            int scaledWidth = 150;
            int scaledHeight = 150;
            ImageResizer.resize("src\\pictures\\" + icon+ ".png", "src\\pictures\\" + icon+ "2.png", scaledWidth, scaledHeight);
            JLabel image = new imageLabel(icon+"2");

            ImageResizer.resize("src\\pictures\\iconHumidity.png", "src\\pictures\\iconHumidity.png", 100, 100);
            JLabel imageHumidity = new imageLabel("iconHumidity");



            image.setBounds(0,0,scaledWidth,scaledHeight);
            add(image);

            descriptionLabel.setBounds(150,50,150,50);
            new textResizer(descriptionLabel);
            add(descriptionLabel);

            currentTemp.setBounds(10,160,150,200);
            new textResizer(currentTemp);
            add(currentTemp);

            windSpeed.setBounds(180, 160, 100,200);
            new textResizer(windSpeed);
            add(windSpeed);

            imageHumidity.setBounds(10,300,100,100);
            add(imageHumidity);

            currentHumidity.setBounds(105, 320, 150,50);
            new textResizer(currentHumidity);
            add(currentHumidity);

            String backPath = "";

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

            back.setBounds(0,400,480,274);
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



