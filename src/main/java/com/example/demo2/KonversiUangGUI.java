package com.example.demo2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;

public class KonversiUangGUI extends Application {
    private static final double MINIMAL_UANG = 100000;
    private static final double MAKSIMAL_UANG = 1000000;

    private ComboBox<String> mataUangComboBox;
    private TextField jumlahUangField;
    private TextArea hasilKonversiArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Konversi Uang");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // ComboBox untuk memilih mata uang
        mataUangComboBox = new ComboBox<>();
        mataUangComboBox.getItems().addAll("Dollar Amerika", "Yuan China", "Yen Jepang", "Riyal Arab Saudi");
        mataUangComboBox.setPromptText("Pilih Mata Uang");
        grid.add(mataUangComboBox, 0, 0, 2, 1);

        // TextField untuk memasukkan jumlah uang
        jumlahUangField = new TextField();
        jumlahUangField.setPromptText("Masukkan Jumlah Uang (Rupiah)");
        grid.add(jumlahUangField, 0, 1, 2, 1);

        // Button untuk melakukan konversi
        Button konversiButton = new Button("Konversi");
        konversiButton.setOnAction(e -> konversiUang());
        grid.add(konversiButton, 0, 2);

        // TextArea untuk menampilkan hasil konversi
        hasilKonversiArea = new TextArea();
        hasilKonversiArea.setEditable(false);
        grid.add(hasilKonversiArea, 0, 3, 2, 1);

        // Button untuk menampilkan riwayat konversi
        Button riwayatButton = new Button("Tampilkan Riwayat");
        riwayatButton.setOnAction(e -> tampilkanRiwayat());
        grid.add(riwayatButton, 1, 2);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void konversiUang() {
        try {
            String selectedMataUang = mataUangComboBox.getValue();
            if (selectedMataUang == null) {
                showAlert("Pilih mata uang terlebih dahulu.");
                return;
            }

            int pilihanMataUang = mataUangComboBox.getItems().indexOf(selectedMataUang) + 1;
            double nilaiTukar = getNilaiTukar(pilihanMataUang);

            double jumlahRupiah = Double.parseDouble(jumlahUangField.getText());
            if (jumlahRupiah < MINIMAL_UANG || jumlahRupiah > MAKSIMAL_UANG) {
                showAlert("Jumlah uang harus antara Rp" + MINIMAL_UANG + " dan Rp" + MAKSIMAL_UANG + ".");
                return;
            }

            double hasilKonversi = jumlahRupiah * nilaiTukar;

            // Format hasil konversi dengan nama mata uang
            String mataUang = getNamaMataUang(pilihanMataUang);
            hasilKonversiArea.setText("Hasil konversi: " + hasilKonversi + " " + mataUang);

            simpanRiwayat(jumlahRupiah, pilihanMataUang, hasilKonversi);

            // Clear input
            jumlahUangField.clear();

        } catch (NumberFormatException e) {
            showAlert("Masukkan jumlah uang yang valid.");
        }
    }

    private void tampilkanRiwayat() {
        try (BufferedReader reader = new BufferedReader(new FileReader("riwayat_konversi.txt"))) {
            StringBuilder riwayat = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                riwayat.append(line).append("\n");
            }
            if (riwayat.length() == 0) {
                showAlert("Riwayat konversi kosong.");
            } else {
                hasilKonversiArea.setText("Riwayat Konversi:\n" + riwayat.toString());
            }
        } catch (IOException e) {
            showAlert("Maaf, Belum ada Riwayat Konversi. Silahkan melakukan Konversi.");
        }
    }

    private double getNilaiTukar(int pilihanMataUang) {
        switch (pilihanMataUang) {
            case 1:
                return 0.000065;
            case 2:
                return 0.00046;
            case 3:
                return 0.0095;
            case 4:
                return 0.00024;
            default:
                throw new IllegalArgumentException("Pilihan mata uang tidak valid.");
        }
    }

    private void simpanRiwayat(double jumlahRupiah, int pilihanMataUang, double hasilKonversi) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("riwayat_konversi.txt", true))) {
            writer.println("Jumlah Rupiah: Rp" + jumlahRupiah);
            writer.println("Mata Uang Dipilih: " + getNamaMataUang(pilihanMataUang));
            writer.println("Hasil Konversi: " + hasilKonversi);
            writer.println("-----------------------");
        } catch (IOException e) {
            showAlert("Gagal menyimpan riwayat konversi ke dalam file.");
        }
    }

    private String getNamaMataUang(int pilihanMataUang) {
        switch (pilihanMataUang) {
            case 1:
                return "Dollar Amerika";
            case 2:
                return "Yuan China";
            case 3:
                return "Yen Jepang";
            case 4:
                return "Riyal Arab Saudi";
            default:
                throw new IllegalArgumentException("Pilihan mata uang tidak valid.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}