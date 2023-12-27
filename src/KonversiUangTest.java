import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class KonversiUangTest {

    @Test
    public void testKonversiDollarAmerika() {
        int pilihanMataUang = 1; // Dollar Amerika
        double nilaiTukar = 0.000065;
        double jumlahRupiah = 500000;

        double expectedHasilKonversi = jumlahRupiah * nilaiTukar;
        double actualHasilKonversi = hitungKonversiUang(jumlahRupiah, pilihanMataUang);

        assertEquals(expectedHasilKonversi, actualHasilKonversi, 0.0001);
    }

    @Test
    public void testKonversiYuanChina() {
        int pilihanMataUang = 2; // Yuan China
        double nilaiTukar = 0.00046;
        double jumlahRupiah = 500000;

        double expectedHasilKonversi = jumlahRupiah * nilaiTukar;
        double actualHasilKonversi = hitungKonversiUang(jumlahRupiah, pilihanMataUang);

        assertEquals(expectedHasilKonversi, actualHasilKonversi, 0.0001);
    }

    @Test
    public void testKonversiYenJepang() {
        int pilihanMataUang = 3; // Yen Jepang
        double nilaiTukar = 0.0095;
        double jumlahRupiah = 500000;

        double expectedHasilKonversi = jumlahRupiah * nilaiTukar;
        double actualHasilKonversi = hitungKonversiUang(jumlahRupiah, pilihanMataUang);

        assertEquals(expectedHasilKonversi, actualHasilKonversi, 0.0001);
    }

    @Test
    public void testKonversiRiyalArabSaudi() {
        int pilihanMataUang = 4; // Riyal Arab Saudi
        double nilaiTukar = 0.00024;
        double jumlahRupiah = 500000;

        double expectedHasilKonversi = jumlahRupiah * nilaiTukar;
        double actualHasilKonversi = hitungKonversiUang(jumlahRupiah, pilihanMataUang);

        assertEquals(expectedHasilKonversi, actualHasilKonversi, 0.0001);
    }

    private double hitungKonversiUang(double jumlahRupiah, int pilihanMataUang) {
        double nilaiTukar;
        switch (pilihanMataUang) {
            case 1:
                nilaiTukar = 0.000065;
                break;
            case 2:
                nilaiTukar = 0.00046;
                break;
            case 3:
                nilaiTukar = 0.0095;
                break;
            case 4:
                nilaiTukar = 0.00024;
                break;
            default:
                throw new IllegalArgumentException("Pilihan mata uang tidak valid.");
        }

        return jumlahRupiah * nilaiTukar;
    }
}
