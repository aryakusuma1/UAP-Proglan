import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Program untuk melakukan konversi uang antara Rupiah dan beberapa mata uang asing.
 */
public class KonversiUang {

    /** Daftar mata uang yang dapat dipilih untuk konversi. */
    private static final List<String> MATA_UANG_OPTIONS = List.of(
            "Dollar Amerika",
            "Yuan China",
            "Yen Jepang",
            "Riyal Arab Saudi"
    );

    /** Scanner untuk input dari pengguna. */
    private static final Scanner scanner = new Scanner(System.in);

    /** Nama file untuk menyimpan riwayat konversi. */
    private static final String RIWAYAT_FILE = "riwayat_konversi.txt";

    /** Nilai minimal dan maksimal uang yang dapat dikonversi. */
    private static final double MINIMAL_UANG = 100000;
    private static final double MAKSIMAL_UANG = 1000000;

    /**
     * Metode utama untuk menjalankan program konversi uang.
     *
     * @param args Argumen baris perintah (tidak digunakan dalam program ini).
     */
    public static void main(String[] args) {
        int menu;
        do {
            try {
                tampilkanMenu();
                System.out.print("Pilih menu: ");
                menu = scanner.nextInt();

                switch (menu) {
                    case 1:
                        konversiUang();
                        break;
                    case 2:
                        tampilkanRiwayat();
                        break;
                    case 3:
                        System.out.println("Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Masukkan angka sesuai pilihan menu.");
                scanner.nextLine(); // Membersihkan buffer input
                menu = 0; // Inisialisasi menu dengan nilai yang tidak valid agar loop berlanjut
            }
        } while (menu != 3);

        scanner.close();
    }

    /**
     * Menampilkan menu utama program konversi uang.
     */
    private static void tampilkanMenu() {
        System.out.println("Program Konversi Uang:");
        System.out.println("1. Konversi Uang");
        System.out.println("2. Tampilkan Riwayat Konversi");
        System.out.println("3. Keluar");
    }

    /**
     * Menampilkan pilihan mata uang yang dapat dipilih untuk konversi.
     */
    private static void tampilkanPilihanMataUang() {
        System.out.println("Pilih mata uang untuk konversi:");
        for (int i = 0; i < MATA_UANG_OPTIONS.size(); i++) {
            System.out.println((i + 1) + ". " + MATA_UANG_OPTIONS.get(i));
        }
    }

    /**
     * Mendapatkan nilai tukar untuk mata uang yang dipilih.
     *
     * @param pilihanMataUang Nomor mata uang yang dipilih.
     * @return Nilai tukar untuk mata uang yang dipilih.
     * @throws IllegalArgumentException Jika nomor mata uang tidak valid.
     */
    private static double getNilaiTukar(int pilihanMataUang) {
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

    /**
     * Mendapatkan nama mata uang berdasarkan nomor mata uang yang dipilih.
     *
     * @param pilihanMataUang Nomor mata uang yang dipilih.
     * @return Nama mata uang.
     * @throws IllegalArgumentException Jika nomor mata uang tidak valid.
     */
    private static String getNamaMataUang(int pilihanMataUang) {
        if (pilihanMataUang < 1 || pilihanMataUang > MATA_UANG_OPTIONS.size()) {
            throw new IllegalArgumentException("Pilihan mata uang tidak valid.");
        }
        return MATA_UANG_OPTIONS.get(pilihanMataUang - 1);
    }

    /**
     * Metode untuk melakukan konversi uang.
     */
    private static void konversiUang() {
        try {
            tampilkanPilihanMataUang();
            System.out.print("Masukkan nomor mata uang yang dipilih: ");
            int pilihanMataUang = scanner.nextInt();

            double nilaiTukar = getNilaiTukar(pilihanMataUang);

            double jumlahRupiah;
            do {
                System.out.print("Masukkan jumlah uang (dalam Rupiah) antara Rp" + MINIMAL_UANG +
                        " dan Rp" + MAKSIMAL_UANG + ": Rp");
                jumlahRupiah = scanner.nextDouble();

                if (jumlahRupiah < MINIMAL_UANG) {
                    throw new IllegalArgumentException("Maaf, Minimal Konversi Adalah Rp100000");
                } else if (jumlahRupiah > MAKSIMAL_UANG) {
                    throw new IllegalArgumentException("Maaf, Maksimal Konversi Adalah Rp1000000");
                }
            } while (jumlahRupiah < MINIMAL_UANG || jumlahRupiah > MAKSIMAL_UANG);

            double hasilKonversi = jumlahRupiah * nilaiTukar;

            String mataUang = getNamaMataUang(pilihanMataUang);
            System.out.println("Hasil Konversi Uang Anda adalah: " + hasilKonversi + " " + mataUang);

            simpanRiwayat(jumlahRupiah, pilihanMataUang, hasilKonversi);

            System.out.print("Apakah Anda ingin melakukan konversi lagi? (ya/tidak): ");
            String lanjutkan = scanner.next().toLowerCase();

            if (!lanjutkan.equals("ya")) {
                System.out.println("Terima kasih!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
            scanner.nextLine(); // Membersihkan buffer input
        }
    }

    /**
     * Metode untuk menyimpan riwayat konversi ke dalam file.
     *
     * @param jumlahRupiah Jumlah uang dalam Rupiah.
     * @param pilihanMataUang Nomor mata uang yang dipilih.
     * @param hasilKonversi Hasil konversi uang.
     */
    private static void simpanRiwayat(double jumlahRupiah, int pilihanMataUang, double hasilKonversi) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RIWAYAT_FILE, true))) {
            writer.println("Jumlah Rupiah: Rp" + jumlahRupiah);
            writer.println("Mata Uang Dipilih: " + getNamaMataUang(pilihanMataUang));
            writer.println("Hasil Konversi: " + hasilKonversi);
            writer.println("-----------------------");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan riwayat konversi ke dalam file.");
            e.printStackTrace();
        }
    }

    /**
     * Metode untuk menampilkan riwayat konversi dari file.
     */
    private static void tampilkanRiwayat() {
        System.out.println("\nRiwayat Konversi:");

        try (BufferedReader reader = new BufferedReader(new FileReader(RIWAYAT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Maaf, Belum ada Riwayat Konversi. Silahkan melakukan Konversi.");
        } catch (IOException e) {
            System.out.println("Gagal membaca riwayat konversi dari file.");
            e.printStackTrace();
        }
    }
}