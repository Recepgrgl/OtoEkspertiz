package OtoEkspertizYonetim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AracParcaSecimSonuc extends JFrame {

    private static final long serialVersionUID = 1L;

    public AracParcaSecimSonuc(HashMap<String, Color> buttonColors, HashMap<String, Point> buttonLocations, ImageIcon image) {
        setTitle("Araç Parça Seçim Sonuç");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel(image);
        label.setBounds(47, 62, image.getIconWidth(), image.getIconHeight());
        getContentPane().add(label);

        for (String buttonText : buttonColors.keySet()) {
            JButton button = new JButton(buttonText);
            button.setBounds(buttonLocations.get(buttonText).x, buttonLocations.get(buttonText).y, 150, 30);
            button.setOpaque(true);
            button.setBackground(buttonColors.get(buttonText));
            button.setBorderPainted(false);
            button.setForeground(Color.WHITE);
            button.setVisible(true);
            getContentPane().add(button);
        }

        JButton btnTamam = createTamamButton("Tamam", 200, 650);
        getContentPane().add(btnTamam);

        setVisible(true);

      
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });
    }

    private JButton createTamamButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        return button;
    }

    public static void main(String[] args) {
        HashMap<String, Color> buttonColors = new HashMap<>();
        HashMap<String, Point> buttonLocations = new HashMap<>();

        buttonColors.put("Ön Tampon", Color.BLUE);
        buttonLocations.put("Ön Tampon", new Point(200, 100));

        buttonColors.put("Kaput", Color.RED);
        buttonLocations.put("Kaput", new Point(200, 180));

        new AracParcaSecimSonuc(buttonColors, buttonLocations, new ImageIcon("C:/OtoEkspertizYonetim/img/eksper.png")).setVisible(true);
    }
}
