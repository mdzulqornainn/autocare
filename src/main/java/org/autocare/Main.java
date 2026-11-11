package org.autocare;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {
    private ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
    private Stage window;
    private Scene loginScene, mainScene;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Sistem Manajemen AutoCare");

        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(20));
        loginGrid.setVgap(10);
        loginGrid.setHgap(10);
        loginGrid.setAlignment(Pos.CENTER);

        Label lblUser = new Label("Username:");
        TextField txtUser = new TextField();

        Label lblPass = new Label("Password:");
        PasswordField txtPass = new PasswordField();

        Button btnLogin = new Button("Login");
        Label lblLoginStatus = new Label();
        lblLoginStatus.setStyle("-fx-text-fill: red;");

        loginGrid.add(lblUser, 0, 0); loginGrid.add(txtUser, 1, 0);
        loginGrid.add(lblPass, 0, 1); loginGrid.add(txtPass, 1, 1);
        loginGrid.add(btnLogin, 1, 2);
        loginGrid.add(lblLoginStatus, 1, 3);

        loginScene = new Scene(loginGrid, 300, 200);

        btnLogin.setOnAction(e -> {
            String username = txtUser.getText();
            String password = txtPass.getText();

            if(cekLogin(username, password)) {
                System.out.println("Login Sukses!");
                setupMainScene(); // Siapkan scene utama
                window.setScene(mainScene); // Pindah ke scene utama
            } else {
                lblLoginStatus.setText("Username / Password salah!");
            }
        });

        window.setScene(loginScene);
        window.show();
    }

    private boolean cekLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // --- SCENE 2: MENU UTAMA KENDARAAN ---
    private void setupMainScene() {
        Label lblTipe = new Label("Tipe Kendaraan:");
        ComboBox<String> cbTipe = new ComboBox<>();
        cbTipe.getItems().addAll("Mobil", "Motor");
        cbTipe.setValue("Mobil");

        Label lblPlat = new Label("Plat Nomor:");
        TextField txtPlat = new TextField();

        Label lblMerk = new Label("Merk:");
        TextField txtMerk = new TextField();

        Label lblPemilik = new Label("Pemilik:");
        TextField txtPemilik = new TextField();

        Label lblKhusus = new Label("Jml Pintu / Transmisi:");
        TextField txtKhusus = new TextField();

        Button btnTambah = new Button("Tambah Kendaraan");
        TextArea txtAreaOutput = new TextArea();
        txtAreaOutput.setEditable(false);

        GridPane grid = new GridPane();
        grid.setVgap(10); grid.setHgap(10);
        grid.add(lblTipe, 0, 0); grid.add(cbTipe, 1, 0);
        grid.add(lblPlat, 0, 1); grid.add(txtPlat, 1, 1);
        grid.add(lblMerk, 0, 2); grid.add(txtMerk, 1, 2);
        grid.add(lblPemilik, 0, 3); grid.add(txtPemilik, 1, 3);
        grid.add(lblKhusus, 0, 4); grid.add(txtKhusus, 1, 4);
        grid.add(btnTambah, 1, 5);

        // Event Handling Tambah Kendaraan + Insert ke Database
        btnTambah.setOnAction(e -> {
            String tipe = cbTipe.getValue();
            String plat = txtPlat.getText();
            String merk = txtMerk.getText();
            String pemilik = txtPemilik.getText();
            String khusus = txtKhusus.getText();

            if (plat.isEmpty() || merk.isEmpty() || pemilik.isEmpty() || khusus.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Semua field harus diisi!");
                alert.show();
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO kendaraan (tipe, plat_nomor, merk, pemilik, khusus) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, tipe);
                pstmt.setString(2, plat);
                pstmt.setString(3, merk);
                pstmt.setString(4, pemilik);
                pstmt.setString(5, khusus);
                pstmt.executeUpdate();

                // Instansiasi Object (Materi Pertemuan 3)
                if (tipe.equals("Mobil")) {
                    Mobil m = new Mobil(plat, merk, pemilik, Integer.parseInt(khusus));
                    daftarKendaraan.add(m);
                    txtAreaOutput.appendText("Tersimpan di DB & Memori: " + m.getInfo() + "\n");
                } else {
                    Motor m = new Motor(plat, merk, pemilik, khusus);
                    daftarKendaraan.add(m);
                    txtAreaOutput.appendText("Tersimpan di DB & Memori: " + m.getInfo() + "\n");
                }

                txtPlat.clear(); txtMerk.clear(); txtPemilik.clear(); txtKhusus.clear();
            } catch (SQLException | NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Gagal menyimpan: " + ex.getMessage());
                alert.show();
            }
        });

        VBox root = new VBox(15);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(grid, new Label("Daftar Kendaraan:"), txtAreaOutput);

        mainScene = new Scene(root, 450, 500);
    }

    public static void main(String[] args) {
        launch(args);
    }
}