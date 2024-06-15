package OtoEkspertizYonetim;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JTextField;

public class MusteriYonetimModulu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField musteriAdSoyad;
    private JTextField musteriTelNo;
    private JScrollPane scrollPane;
    private JButton btnEkle;
    private JTable table;
    private MusteriModel musteriModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MusteriYonetimModulu frame = new MusteriYonetimModulu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MusteriYonetimModulu() {
        musteriModel = new MusteriModel();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 486);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMteriYnetim = new JLabel("MÜŞTERİ YÖNETİM");
        lblMteriYnetim.setBounds(175, 10, 297, 29);
        lblMteriYnetim.setHorizontalAlignment(SwingConstants.CENTER);
        lblMteriYnetim.setForeground(Color.RED);
        lblMteriYnetim.setFont(new Font("Tahoma", Font.BOLD, 24));
        contentPane.add(lblMteriYnetim);

        JLabel lblNewLabel_1_1 = new JLabel("Müşteri Ad Soyad");
        lblNewLabel_1_1.setBounds(54, 131, 91, 29);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Müşteri Tel No");
        lblNewLabel_1_1_1.setBounds(168, 131, 91, 29);
        contentPane.add(lblNewLabel_1_1_1);

        musteriTelNo = new JTextField();
        musteriTelNo.setBounds(168, 170, 68, 19);
        contentPane.add(musteriTelNo);
        musteriTelNo.setColumns(10);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(54, 229, 533, 186);
        contentPane.add(scrollPane);

        btnEkle = new JButton("Ekle");
        btnEkle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adSoyad = musteriAdSoyad.getText();
                String telNo = musteriTelNo.getText();
                musteriAdSoyad.setText("");
                musteriTelNo.setText("");
                try {
                    musteriModel.addCustomer(adSoyad, telNo);
                    updateTable();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Müşteri eklenirken bir hata oluştu: " + ex.getMessage());
                }
            }
        });
        btnEkle.setForeground(new Color(0, 128, 0));
        btnEkle.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEkle.setBounds(281, 141, 84, 49);
        contentPane.add(btnEkle);

        JButton btnSil = new JButton("Sil");
        btnSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Lütfen silinecek bir satır seçin.");
                    return;
                }

                int musteriID = (int) table.getValueAt(selectedRow, 0);

                try {
                    musteriModel.deleteCustomer(musteriID);
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Müşteri başarıyla silindi.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Müşteri silinirken bir hata oluştu: " + ex.getMessage());
                }
            }
        });

        btnSil.setForeground(Color.RED);
        btnSil.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSil.setBounds(375, 140, 84, 49);
        contentPane.add(btnSil);

        JButton btnGuncelle = new JButton("Güncelle");

        btnGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Lütfen güncellenecek bir satır seçin.");
                    return;
                }

                int musteriID = (int) table.getValueAt(selectedRow, 0);
                String adSoyad = musteriAdSoyad.getText();
                String telNo = musteriTelNo.getText();

                try {
                    musteriModel.updateCustomer(musteriID, adSoyad, telNo);
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Müşteri başarıyla güncellendi.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Müşteri güncellenirken bir hata oluştu: " + ex.getMessage());
                }
            }
        });

        btnGuncelle.setForeground(Color.BLUE);
        btnGuncelle.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGuncelle.setBounds(535, 142, 91, 49);
        contentPane.add(btnGuncelle);

        musteriAdSoyad = new JTextField();
        musteriAdSoyad.setColumns(10);
        musteriAdSoyad.setBounds(54, 170, 68, 19);
        contentPane.add(musteriAdSoyad);

        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnNewButton = new JButton("Ana Menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnaMenu anaMenu = new AnaMenu();
                anaMenu.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(10, 10, 103, 37);
        contentPane.add(btnNewButton);

        updateTable();
    }

    private void updateTable() {
        try {
            DefaultTableModel model = musteriModel.getCustomerTableModel();
            table.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tablo güncellenirken bir hata oluştu: " + ex.getMessage());
        }

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int musteriID = (int) table.getValueAt(selectedRow, 0);
                    try {
                        musteriModel.fillCustomerFields(musteriID, musteriAdSoyad, musteriTelNo);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Müşteri bilgileri alınırken bir hata oluştu: " + ex.getMessage());
                    }
                }
            }
        });
    }
}