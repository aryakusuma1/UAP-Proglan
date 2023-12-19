import java.util.Scanner;

/**
 * Program KonversiUang adalah aplikasi sederhana untuk mengonversi jumlah uang
 * dalam Rupiah ke beberapa mata uang asing.
 *
 * Program ini meminta pengguna untuk memasukkan jumlah uang dalam Rupiah dan
 * memilih mata uang yang diinginkan. Kemudian, program akan menghitung dan
 * menampilkan hasil konversi ke mata uang yang dipilih.
 *
 * Program menggunakan metode konversiUang untuk melakukan perhitungan konversi
 * berdasarkan nilai tukar mata uang yang telah ditentukan.
 */
public class KonversiUang {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Metode utama program yang memungkinkan pengguna melakukan konversi uang
     * dalam Rupiah ke mata uang asing yang dipilih.
     *
     * Program akan terus berjalan dalam loop do-while sampai pengguna memilih
     * untuk keluar. Setiap iterasi meminta pengguna untuk memasukkan jumlah uang,
     * memilih mata uang, dan menampilkan hasil konversi.
     *
     * @param args Argumen baris perintah (tidak digunakan).
     */
    public static void main(String[] args) {
        do {
            try {
                // Meminta pengguna untuk memasukkan jumlah uang dalam Rupiah
                System.out.print("Masukkan jumlah uang (dalam Rupiah): Rp");
                double jumlahRupiah = scanner.nextDouble();

                // Menampilkan pilihan mata uang
                System.out.println("Pilih mata uang untuk konversi:");
                System.out.println("1. Dollar Amerika");
                System.out.println("2. Yuan China");
                System.out.println("3. Yen Jepang");
                System.out.println("4. Riyal Arab Saudi");

                // Meminta pengguna untuk memilih mata uang
                System.out.print("Masukkan nomor mata uang yang dipilih: ");
                int pilihanMataUang = scanner.nextInt();

                // Konversi uang berdasarkan pilihan pengguna
                double hasilKonversi = konversiUang(jumlahRupiah, pilihanMataUang);

                // Menampilkan hasil konversi
                System.out.println("Hasil konversi: " + hasilKonversi);

                // Meminta input apakah ingin melakukan konversi lagi
                System.out.print("Apakah Anda ingin melakukan konversi lagi? (ya/tidak): ");
                String lanjutkan = scanner.next().toLowerCase();

                // Mengakhiri loop jika pengguna tidak ingin melanjutkan
                if (!lanjutkan.equals("ya")) {
                    System.out.println("Terima kasih!");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
                // Membersihkan buffer input
                scanner.nextLine();
            }
        } while (true);  // Loop akan terus berjalan selama kondisi adalah true

        scanner.close();
    }

    /**
     * Metode untuk mengonversi jumlah uang dalam Rupiah ke mata uang asing.
     *
     * @param jumlahRupiah Jumlah uang dalam Rupiah yang akan dikonversi.
     * @param pilihanMataUang Nomor mata uang yang dipilih oleh pengguna.
     * @return Hasil konversi jumlah uang ke mata uang yang dipilih.
     * @throws IllegalArgumentException Jika pilihan mata uang tidak valid.
     */
    public static double konversiUang(double jumlahRupiah, int pilihanMataUang) {
        double nilaiTukarDollar = 0.000065;
        double nilaiTukarYuan = 0.00046;
        double nilaiTukarYen = 0.0095;
        double nilaiTukarRiyal = 0.00024;

        switch (pilihanMataUang) {
            case 1:
                return jumlahRupiah * nilaiTukarDollar;
            case 2:
                return jumlahRupiah * nilaiTukarYuan;
            case 3:
                return jumlahRupiah * nilaiTukarYen;
            case 4:
                return jumlahRupiah * nilaiTukarRiyal;
            default:
                throw new IllegalArgumentException("Pilihan mata uang tidak valid.");
        }
    }
}