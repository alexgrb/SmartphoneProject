package Smartphone;
import net.miginfocom.swing.MigLayout;
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

import static Smartphone.display.picDirectory;

/**
 * Récupère toutes les données météos et les affiche
 * @author Piranavan Thambirajah & Alex Gharbi
 */
public class GetWeatherData extends JPanel {

    String icon = "01d"; //Nom de l'image par défaut
    String description = "";
    private JPanel weatherDisplay = new JPanel();

    /**
     * Constructeur qui va appeler l'API.
     * On construit la requête avec la clé API et le nom de la ville.
     * Ensuite on parse le JSON afin de récupérer les données nécessaire.
     * Finalement on affiches les données après les avoir mis en forme.
     * @param city
     *          Ville que l'utilisateur a cherché
     */
    public GetWeatherData(String city) {

        setLayout(null);

        weatherDisplay.setLayout(new MigLayout("wrap 3"));

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
            ObjectMapper objectMapper = new ObjectMapper();
            //Le code suivant permet de rentrer en profondeur dans l'arbre JSON
            try {
                JsonNode node = objectMapper.readTree(result.toString());
                for (JsonNode item : node.get("weather")) {
                    icon = item.get("icon").textValue();
                    description = item.get("description").textValue();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

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
            ImageResizer.resize(picDirectory + icon+ ".png", picDirectory + icon+ "2.png", scaledWidth, scaledHeight);
            JLabel image = new imageLabel(icon+"2");

            ImageResizer.resize(picDirectory+"iconHumidity.png", picDirectory+"iconHumidity.png", 50, 50);
            JLabel imageHumidity = new imageLabel("iconHumidity");

            weatherDisplay.setOpaque(false);

            new textResizer(currentTemp,80);
            weatherDisplay.add(currentTemp);

            String backPath = "";
            weatherDisplay.add(image, "wrap");

            ImageResizer.resize(picDirectory + "iconWindSpeed.png", picDirectory + "iconWindSpeed.png", 50, 50);
            JLabel windSpeedIcon= new imageLabel("iconWindSpeed");

            weatherDisplay.add(windSpeedIcon,"split2");

            new textResizer(windSpeed,35);
            weatherDisplay.add(windSpeed, "wrap");


            weatherDisplay.add(imageHumidity,"split2");

            new textResizer(currentHumidity, 35);
            weatherDisplay.add(currentHumidity);

            weatherDisplay.setBounds(0,0,480,600);
            add(weatherDisplay);

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
            ImageIcon imgBack = new ImageIcon(picDirectory+backPath+".gif");
            JLabel back = new JLabel(imgBack);
            back.setBounds(0,450,480,200);
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



