package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MusteriGUI extends JFrame {
    private JTextField txtAd, txtSoyad, txtTelefon, txtEmail;
    private DefaultTableModel model;
    private JTable table;
    private ArrayList<Musteri> musteriList = new ArrayList<>();

    public MusteriGUI() {
        setTitle("Müşteri Takip Paneli");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblWelcome = new JLabel("Müşteri Takip Sistemine Hoşgeldiniz");
        lblWelcome.setBounds(10, 10, 400, 16);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        getContentPane().add(lblWelcome);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(10, 40, 460, 310);
        getContentPane().add(tabbedPane);

        // Müşteri Ekle Paneli
        JPanel pnlEkle = new JPanel(null);
        tabbedPane.addTab("Müşteri Ekle", pnlEkle);

        txtAd = new JTextField(); txtAd.setBounds(20, 20, 150, 25);
        txtSoyad = new JTextField(); txtSoyad.setBounds(20, 60, 150, 25);
        txtTelefon = new JTextField(); txtTelefon.setBounds(20, 100, 150, 25);
        txtEmail = new JTextField(); txtEmail.setBounds(20, 140, 150, 25);

        pnlEkle.add(new JLabel("Ad")).setBounds(180, 20, 100, 20);
        pnlEkle.add(txtAd);
        pnlEkle.add(new JLabel("Soyad")).setBounds(180, 60, 100, 20);
        pnlEkle.add(txtSoyad);
        pnlEkle.add(new JLabel("Telefon")).setBounds(180, 100, 100, 20);
        pnlEkle.add(txtTelefon);
        pnlEkle.add(new JLabel("E-mail")).setBounds(180, 140, 100, 20);
        pnlEkle.add(txtEmail);

        JButton btnEkle = new JButton("Kaydet");
        btnEkle.setBounds(20, 180, 100, 30);
        pnlEkle.add(btnEkle);

        // Müşteri Listesi Paneli
        JPanel pnlListe = new JPanel(new BorderLayout());
        tabbedPane.addTab("Müşteri Listesi", pnlListe);

        model = new DefaultTableModel(new String[]{"Ad", "Soyad", "Telefon", "Email"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        pnlListe.add(scroll, BorderLayout.CENTER);

        JButton btnSil = new JButton("Seçili Müşteriyi Sil");
        pnlListe.add(btnSil, BorderLayout.SOUTH);

        // Kaydet Butonu Olayı
        btnEkle.addActionListener(e -> {
            String ad = txtAd.getText();
            String soyad = txtSoyad.getText();
            String telefon = txtTelefon.getText();
            String email = txtEmail.getText();

            if (ad.isEmpty() || soyad.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ad ve soyad boş olamaz.");
                return;
            }

            Musteri m = new Musteri(ad, soyad, telefon, email);
            musteriList.add(m);
            model.addRow(new Object[]{ad, soyad, telefon, email});

            txtAd.setText(""); txtSoyad.setText(""); txtTelefon.setText(""); txtEmail.setText("");
            JOptionPane.showMessageDialog(null, "Müşteri kaydedildi.");
        });

        // Sil Butonu Olayı
        btnSil.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Lütfen silmek için bir müşteri seçin.");
                return;
            }

            int onay = JOptionPane.showConfirmDialog(null, "Seçili müşteriyi silmek istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (onay == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
                musteriList.remove(selectedRow);
                JOptionPane.showMessageDialog(null, "Müşteri silindi.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MusteriGUI().setVisible(true));
    }

    // Basit Müşteri Sınıfı
    static class Musteri {
        String ad, soyad, telefon, email;

        public Musteri(String ad, String soyad, String telefon, String email) {
            this.ad = ad;
            this.soyad = soyad;
            this.telefon = telefon;
            this.email = email;
        }
    }
}
