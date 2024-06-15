package OtoEkspertizYonetim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginEkrani extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField UserTextField;
    private JPasswordField passwordField;

    private String url = "jdbc:postgresql://localhost:5432/otoekspertiz";
    private String username = "postgres";
    private String password = "abc123";
    private Connection con = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginEkrani frame = new LoginEkrani();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginEkrani() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 595, 331);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Kullanıcı Adı");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBounds(49, 40, 136, 38);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Şifre");
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1.setBounds(49, 88, 80, 38);
        contentPane.add(lblNewLabel_1);

        UserTextField = new JTextField();
        UserTextField.setColumns(10);
        UserTextField.setBounds(170, 40, 206, 28);
        contentPane.add(UserTextField);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 88, 206, 28);
        contentPane.add(passwordField);

        JButton btnNewButton = new JButton("Giriş");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton.setForeground(Color.RED);
        btnNewButton.setBounds(170, 160, 85, 21);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = UserTextField.getText();
                String sifre = new String(passwordField.getPassword());
                if (login(kullaniciAdi, sifre)) {
                    JOptionPane.showMessageDialog(null, "Giriş Başarılı!");
                    MainMenu mainMenu = new MainMenu();  
                    mainMenu.setVisible(true);
                    dispose();  
                } else {
                    JOptionPane.showMessageDialog(null, "Kullanıcı Adı veya Şifre Hatalı!");
                }
            }
        });
        contentPane.add(btnNewButton);
    }

    public boolean login(String kullaniciAdi, String sifre) {
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM adminler WHERE kullanici_adi = ? AND sifre = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, kullaniciAdi);
            pstmt.setString(2, sifre);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
