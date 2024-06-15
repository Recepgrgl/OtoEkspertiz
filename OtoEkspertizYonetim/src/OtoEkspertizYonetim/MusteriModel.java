package OtoEkspertizYonetim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MusteriModel {
    private final String url = "jdbc:postgresql://localhost:5432/otoekspertiz";
    private final String user = "postgres";
    private final String password = "abc123";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addCustomer(String adSoyad, String telNo) throws SQLException {
        String SQL = "INSERT INTO musteri_yonetim_modulu (musteri_adsoyad, musteri_telno) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, adSoyad);
            pstmt.setString(2, telNo);
            pstmt.executeUpdate();
        }
    }

    public void deleteCustomer(int musteriID) throws SQLException {
        String SQL = "DELETE FROM musteri_yonetim_modulu WHERE musteri_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, musteriID);
            pstmt.executeUpdate();
        }
    }

    public void updateCustomer(int musteriID, String adSoyad, String telNo) throws SQLException {
        String SQL = "UPDATE musteri_yonetim_modulu SET musteri_adsoyad = ?, musteri_telno = ? WHERE musteri_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, adSoyad);
            pstmt.setString(2, telNo);
            pstmt.setInt(3, musteriID);
            pstmt.executeUpdate();
        }
    }

    public DefaultTableModel getCustomerTableModel() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Müşteri ID");
        model.addColumn("Müşteri Ad Soyad");
        model.addColumn("Müşteri Tel No");

        String SQL = "SELECT * FROM musteri_yonetim_modulu ORDER BY musteri_id ASC";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("musteri_id"),
                    rs.getString("musteri_adsoyad"),
                    rs.getString("musteri_telno")
                });
            }
        }
        return model;
    }

    public void fillCustomerFields(int musteriID, JTextField adSoyadField, JTextField telNoField) throws SQLException {
        String SQL = "SELECT * FROM musteri_yonetim_modulu WHERE musteri_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, musteriID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                adSoyadField.setText(rs.getString("musteri_adsoyad"));
                telNoField.setText(rs.getString("musteri_telno"));
            }
        }
    }
}