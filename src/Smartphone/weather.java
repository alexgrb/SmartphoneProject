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

public class weather extends JPanel {

    //Array to other apps
    protected String[] cities = {"Montreux", "Sion", "Lausanne", "Zurich"};
    JButton montreux = new JButton("Montreux");
    JLabel cityLabel = new JLabel("Lausanne");

    public void setMontreux(JButton montreux) {
        this.montreux = montreux;
    }

    public JComboBox cityList;

        public weather() {
            Object[] cities = new Object[]{"Lausanne,CH", "Sion,CH", "Zurich,CH", "Montreux,CH"};
            cityList = new JComboBox(cities);
            cityList.addActionListener(new comboListener());
            add(cityList);


            add(cityLabel);

            montreux.addActionListener(new comboListener());


            //add(new GetWeatherData(cityList.getSelectedItem().toString()));
        }

    class comboListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
               int i =0;
               JPanel montreuxPanel = new GetWeatherData("Montreux");
               add(montreuxPanel);

                String s = ((GetWeatherData) montreuxPanel).showModal();
                cityLabel.setText(s);

            }
        }
    public void setCityLabel(String cityLabel) {
        this.cityLabel.setText(cityLabel);

        }
}

