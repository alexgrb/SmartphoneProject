package tools;

import Smartphone.GetWeatherData;
import Smartphone.weather;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.*;

/**
 * Classe qui permet l'autocomplétion
 * @author Piranavan Thambirajah & Alex Gharbi
 * Créé en mai 2019
 */
public class autoComplete extends JPanel {

    public static JTextField txtName;
    public static JComboBox<String> comboBox;
    private String[] cityList2;
    private weather weat;

    public autoComplete(String[] city, JComboBox comboBoxx) {
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
        //On va écouter chaque entrée sur le clavier
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 38) { //Monte dans la comobox
                    int x = comboBox.getSelectedIndex();
                    if (x > 0) {
                        comboBox.setSelectedIndex(x - 1);
                        System.out.println("Monte");
                    }
                    add(comboBox);
                    comboBox.showPopup();
                } else if (e.getKeyCode() == 40) { //Descend dans la comboBox
                    int x = comboBox.getSelectedIndex();
                    int y = comboBox.getItemCount();
                    if (x + 1 < y)
                        comboBox.setSelectedIndex(x + 1);
                    add(comboBox);
                    comboBox.showPopup();
                }
            }
        });
        //Enlève la combobox dès qu'on a choisi une ville
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
    }

    /**
     * Cherche une ville dans le tableau de string dès qu'on écrit une lettre. (min 2)
     */
    private class TextFieldCaretListener implements CaretListener {
        public void caretUpdate(CaretEvent e) {
            try {
                comboBox.removeAllItems();
                comboBox.hidePopup();
                remove(comboBox);
                if (e.getMark() > 0) {
                    for (String string : cityList2) { //Cherche des villes qui correspondent aux lettres dans le JTextField
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
}