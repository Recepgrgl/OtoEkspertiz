package OtoEkspertizYonetim;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public MainMenu() {
        setTitle("Ana Menü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWelcome = new JLabel("Hoşgeldiniz!");
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblWelcome.setBounds(150, 50, 200, 50);
        contentPane.add(lblWelcome);

        JButton btnAracParcaSecim = new JButton("Araç Parça Seçim");
        btnAracParcaSecim.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAracParcaSecim.setBounds(140, 120, 160, 30);
        contentPane.add(btnAracParcaSecim);

        btnAracParcaSecim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AracParcaSecim aracParcaSecim = new AracParcaSecim();
                aracParcaSecim.arabaParcaSecimPenceresiniGoster();
                dispose();
            }
        });
    }
}
