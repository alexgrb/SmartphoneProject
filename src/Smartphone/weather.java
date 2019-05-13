package Smartphone;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class weather extends JPanel {

    //Array to other apps
    public JPanel contentWeather = new GetWeatherData("Lausanne,CH");
    public JComboBox cityList;
    private JLabel cityLabel= new JLabel();

        public weather() {
            Object[] cities = new Object[]{"Lausanne,CH", "Sion,CH", "Zurich,CH", "Montreux,CH", "Sierre,CH"};
            cityList = new JComboBox(cities);
            cityList.addActionListener(new comboListener());
            add(cityList);

            setLayout(new BorderLayout());

            add(cityList, BorderLayout.NORTH);
            add(contentWeather, BorderLayout.CENTER);

        }

    class comboListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {


              contentWeather.removeAll();

               JPanel weatherPanel = new GetWeatherData(cityList.getSelectedItem().toString());
                contentWeather.add(weatherPanel,BorderLayout.CENTER);
               contentWeather.validate();
                contentWeather.repaint();
            setVisible(true);
            }
        }
}

