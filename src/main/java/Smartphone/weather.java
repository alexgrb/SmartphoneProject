package Smartphone;

import tools.imageButton;
import tools.imageLabel;
import tools.autoComplete;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class weather extends JPanel {

    //Array to other apps
    public JPanel contentWeather = new GetWeatherData("Lausanne,CH");
    public JComboBox cityList;
    private JButton syncCity = new imageButton("iconSync48");
    String[] cities = new String[]{"Lausanne,CH", "Sion,CH", "Zurich,CH", "Montreux,CH", "Sierre,CH", "Dubai, AE"};
    private JPanel textField = new autoComplete(cities, cityList, this);


    public weather() {
            syncCity.setBounds(165, 40, 48, 48);
            syncCity.addActionListener(new comboListener());
            add(syncCity);
            cityList = new JComboBox(cities);
            cityList.addActionListener(new comboListener());
            setLayout(null);
            JLabel backGround = new imageLabel("weatherBack");
            backGround.setBounds(0,0,480,800);

            textField.setBounds(10,40,200,50);
            add(textField);
            contentWeather.setBounds(0,55,480,745);
            contentWeather.setOpaque(false);
            add(contentWeather);
            add(backGround);
        }

    public class comboListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentWeather.removeAll();
                JPanel weatherPanel = new GetWeatherData(autoComplete.txtName.getText()); //photo
                contentWeather.setLayout(null);
                weatherPanel.setOpaque(false);
                weatherPanel.setBounds(0,0,480,745);
                contentWeather.add(weatherPanel);
                contentWeather.validate();
                contentWeather.repaint();
                setVisible(true);
            }
        }        //mdr

}

