package OtoEkspertizYonetim;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnaMenu extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AnaMenu frame = new AnaMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public AnaMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 837, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAnaMen = new JLabel("ANA MENÜ");
        lblAnaMen.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnaMen.setForeground(new Color(255, 0, 0));
        lblAnaMen.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblAnaMen.setBounds(243, 10, 300, 53);
        contentPane.add(lblAnaMen);

        JButton btnMusteriYonetim = new JButton("MÜŞTERİ YÖNETİM");
        btnMusteriYonetim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MusteriYonetimModulu musteriYonetimModulu = new MusteriYonetimModulu();
                musteriYonetimModulu.setVisible(true);
                dispose();
            }
        });
        btnMusteriYonetim.setForeground(new Color(0, 128, 255));
        btnMusteriYonetim.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnMusteriYonetim.setBounds(24, 77, 215, 59);
        contentPane.add(btnMusteriYonetim);

        JButton btnFotoğrafEkleButton = new JButton("FOTOĞRAF EKLE");
        btnFotoğrafEkleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AracParcaSecim aracParcaSecim = new AracParcaSecim();
                aracParcaSecim.arabaParcaSecimPenceresiniGoster();
            }
        });
        btnFotoğrafEkleButton.setForeground(new Color(0, 128, 255));
        btnFotoğrafEkleButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnFotoğrafEkleButton.setBounds(526, 80, 215, 53);
        contentPane.add(btnFotoğrafEkleButton);
    }
}
