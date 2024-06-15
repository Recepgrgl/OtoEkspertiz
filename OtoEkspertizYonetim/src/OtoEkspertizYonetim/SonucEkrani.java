package OtoEkspertizYonetim;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SonucEkrani extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    
    public SonucEkrani() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 837, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSonuclar = new JLabel("SONUÇLAR");
        lblSonuclar.setHorizontalAlignment(SwingConstants.CENTER);
        lblSonuclar.setForeground(new Color(255, 0, 0));
        lblSonuclar.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblSonuclar.setBounds(300, 10, 200, 53);
        contentPane.add(lblSonuclar);

        

        JButton btnKapat = new JButton("KAPAT");
        btnKapat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnKapat.setForeground(new Color(0, 128, 0));
        btnKapat.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnKapat.setBounds(355, 420, 117, 40);
        contentPane.add(btnKapat);
    }

    public void sonuclariGoster() {
        setVisible(true);
    }
}
