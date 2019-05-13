package Smartphone;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class weather extends JPanel {

    //Array to other apps
    JButton montreux = new JButton("Montreux");
    JLabel cityLabel = new JLabel("Lausanne");
    JPanel contentWeather = new JPanel();

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

            setLayout(new FlowLayout());
            add(contentWeather);
        }

    class comboListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
               int i =0;
               JPanel montreuxPanel = new GetWeatherData(cityList.getSelectedItem().toString());

               remove(contentWeather);
               contentWeather = montreuxPanel;
               add(contentWeather);
                String s = ((GetWeatherData) montreuxPanel).getCityLabel();
                cityLabel.setText(s);
            }
        }
    public void setCityLabel(String cityLabel) {
        this.cityLabel.setText(cityLabel);
        }
}

