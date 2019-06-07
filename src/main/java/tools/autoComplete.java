package tools;

import Smartphone.GetWeatherData;
import Smartphone.weather;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.*;

public class autoComplete extends JPanel {

    public static JTextField txtName;
    public static JComboBox<String> comboBox;
    private String[] cityList2;
    private weather weat;

    public autoComplete(String[] city, JComboBox comboBoxx,weather weat) {
        cityList2 = city;
        this.comboBox = comboBoxx;

        setOpaque(false);

        setAutoscrolls(true);
        setLayout(null);

        txtName = new JTextField();
        txtName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                txtName.setText(null);
            }
        });
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 38) {
                    int x = comboBox.getSelectedIndex();
                    if (x > 0) {
                        comboBox.setSelectedIndex(x - 1);
                    }
                    add(comboBox);
                    comboBox.showPopup();
                } else if (e.getKeyCode() == 40) {
                    int x = comboBox.getSelectedIndex();
                    int y = comboBox.getItemCount();
                    if (x + 1 < y)
                        comboBox.setSelectedIndex(x + 1);
                    add(comboBox);
                    comboBox.showPopup();
                }
            }
        });
        txtName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    txtName.setText(comboBox.getSelectedItem().toString());
                    comboBox.removeAllItems();
                    comboBox.hidePopup();
                    remove(comboBox);
                } catch (Exception e) {
                }
            }
        });
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName.setText("City");
        txtName.addCaretListener(new TextFieldCaretListener());
        txtName.setBounds(5, 5, 150, 40);
        add(this.txtName);
        txtName.setColumns(10);

        comboBox = new JComboBox<String>();
        comboBox.setFocusCycleRoot(true);
        comboBox.setFocusTraversalPolicyProvider(true);
        comboBox.setAutoscrolls(true);
        comboBox.setBorder(null);
        comboBox.setOpaque(false);
        comboBox.setBounds(5, 5, 149, 37);
        // this.contentPane.add(comboBox);
    }

    private class TextFieldCaretListener implements CaretListener {
        public void caretUpdate(CaretEvent e) {

            try {
                comboBox.removeAllItems();
                comboBox.hidePopup();
                remove(comboBox);
                if (e.getMark() > 0) {
                    for (String string : cityList2) {
                        if (string.toLowerCase().startsWith(txtName.getText().toLowerCase())) {
                            add(comboBox);
                            comboBox.addItem(string);
                            comboBox.showPopup();
                        }
                    }
                }
            } catch (Exception e1) {
            }
            if (e.getMark() < 2) {
                remove(comboBox);
            }
        }
    }
    public class comboListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           // weat.setCityList(comboBox.getSelectedItem().toString());
            System.out.println(comboBox.getSelectedItem().toString());
        }
    }        //mdr

}