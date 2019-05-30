package tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class time {

    ////PAS ENCORE FINI A VOIR
    private static JLabel currentTime;
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public time() {
        int interval = 1000; // 1000 ms
        Calendar now = Calendar.getInstance();
        currentTime = new JLabel(dateFormat.format(now.getTime()));
        new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                currentTime.setText(dateFormat.format(now.getTime()));
            }
        }).start();
    }

    public static JLabel getCurrentTime() {
        return currentTime;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }
}
