package Smartphone;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class weather extends JPanel {

    //Array to other apps
    public JPanel contentWeather = new GetWeatherData("Lausanne,CH");
    public JComboBox cityList;

        public weather() {
            Object[] cities = new Object[]{"Lausanne,CH", "Sion,CH", "Zurich,CH", "Montreux,CH", "Sierre,CH", "Dubai, AE"};
            cityList = new JComboBox(cities);
            cityList.addActionListener(new comboListener());

            setLayout(null);
            cityList.setBounds(160,10,150,50);
            add(cityList);

            contentWeather.setBounds(0,55,480,745);
            add(contentWeather);
        }

    class comboListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentWeather.removeAll();
                JPanel weatherPanel = new GetWeatherData(cityList.getSelectedItem().toString()); //photo
                contentWeather.setLayout(null);
                weatherPanel.setBounds(0,0,480,745);
                contentWeather.add(weatherPanel);
                contentWeather.validate();
                contentWeather.repaint();
                setVisible(true);
            }
        }
}

