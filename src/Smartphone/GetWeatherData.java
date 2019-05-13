package Smartphone;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class GetWeatherData extends JPanel {

    ImageIcon h = new ImageIcon("src\\pictures\\weatherBackground.jpg");
    JLabel x = new JLabel(h);
    String icon = "01d";
    String description = "";
    String cityLabel="Montreux";

    public GetWeatherData(String city) {
        cityLabel = city;
        setLayout(new GridLayout(5,2));
        String API_KEY = "bca5b91d81e883aa7988a8ff953ea56e"; //API gratuite obtenu sur OpenWeather
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + cityLabel + "&appid=" + API_KEY + "&units=metric";
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
                    icon = "src\\pictures\\" + item.get("icon").textValue() + ".png";
                    description = item.get("description").textValue();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            JLabel descriptionLabel = new JLabel(description);

            ImageIcon weatherIcon = new ImageIcon("src\\pictures\\" + icon + ".png");
            //Ce code permet de récupérer les données en surface du JSON
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

            System.out.println(mainMap.get("description"));
            JLabel currentTemp = new JLabel(mainMap.get("temp") + "°");
            JLabel currentHumidity = new JLabel(mainMap.get("humidity") + "% taux d'humidité");
            JLabel windSpeed = new JLabel(windMap.get("speed") + " km/h");
            JLabel windAngle = new JLabel(windMap.get("deg") + "°");
            JLabel cityLabel = new JLabel(city);
            JLabel image = new JLabel(weatherIcon);

          //  weather.setText(coursField.getText());
        add(currentTemp);

        add(windSpeed);
        add(cityLabel);


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
    public String getCityLabel(){
        return cityLabel;
    }

}



