package OtoEkspertizYonetim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AracParcaSecim extends JFrame {

    private static final long serialVersionUID = 1L;
    private HashMap<String, Color> buttonColors = new HashMap<>();
    private HashMap<String, Point> buttonLocations = new HashMap<>();

    public AracParcaSecim() {
        setTitle("Araç Parça Seçim");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        ImageIcon image = new ImageIcon("C:/OtoEkspertizYonetim/img/eksper.png");
        JLabel label = new JLabel(image);
        label.setBounds(47, 62, image.getIconWidth(), image.getIconHeight());
        getContentPane().add(label);

        createAndAddButton("Ön Tampon", 200, 100);
        createAndAddButton("Kaput", 200, 180);
        createAndAddButton("Ön Sağ Çamurluk", 350, 150);
        createAndAddButton("Sağ Ön Kapı", 350, 280);
        createAndAddButton("Sol Ön Kapı", 50, 280);
        createAndAddButton("Sağ Arka Kapı", 350, 360);
        createAndAddButton("Sol Arka Kapı", 50, 360);
        createAndAddButton("Sağ Arka Çamurluk", 350, 470);
        createAndAddButton("Sol Arka Çamurluk", 50, 470);
        createAndAddButton("Bagaj Kapağı", 200, 470);
        createAndAddButton("Arka Tampon", 200, 520);
        createAndAddButton("Ön Sol Çamurluk", 50, 150);

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

    private void createAndAddButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 30);
        button.setOpaque(true);
        button.setBackground(Color.GRAY);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.addActionListener(new ActionListener() {
            int clickCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                if (clickCount % 3 == 1) {
                    button.setBackground(Color.BLUE);
                } else if (clickCount % 3 == 2) {
                    button.setBackground(Color.RED);
                } else {
                    button.setBackground(Color.GRAY);
                }
                buttonColors.put(button.getText(), button.getBackground());
                buttonLocations.put(button.getText(), button.getLocation());
            }
        });
        buttonColors.put(text, button.getBackground());
        buttonLocations.put(text, new Point(x, y));
        getContentPane().add(button);
    }

    private JButton createTamamButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AracParcaSecimSonuc(buttonColors, buttonLocations, new ImageIcon("C:/OtoEkspertizYonetim/img/eksper.png")).setVisible(true);
                dispose();
            }
        });
        return button;
    }

    public static void main(String[] args) {
        new AracParcaSecim().arabaParcaSecimPenceresiniGoster();
    }

    public void arabaParcaSecimPenceresiniGoster() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    AracParcaSecim frame = new AracParcaSecim();
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
