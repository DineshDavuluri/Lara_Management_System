package Lara.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {

    Thread t;

    Splash() {

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/lara1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 730, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        t = new Thread(this);
        t.start();

        setVisible(true);

        int x = 0;
for (int i = 2; i <= 800; i += 4, x += 1) {
    // Calculate the new width and height
    int width = i + 3 * x;
    int height = i + x / 2;

    // Calculate the new location to keep the center at the same position
    int newX = (getWidth() - width) / 2;
    int newY = (getHeight() - height) / 2;

    setLocation(newX, newY);
    setSize(width, height);

    try {
        Thread.sleep(10);
    } catch (Exception e) {
    }
}
    }

    public void run() {
        try {
            Thread.sleep(4000);
            setVisible(false);

            // Next Frame
            new Login();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
