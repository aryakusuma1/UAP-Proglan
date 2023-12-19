import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class KonversiUangTest {

    @Test
    public void testKonversiUang() {
        // Persiapkan data uji
        double nilaiToleransi = 0.0001;  // Toleransi kesalahan untuk perbandingan nilai double
        double nilaiRupiah = 100000;  // Jumlah uang dalam Rupiah

        // Uji konversi ke Dollar Amerika
        double nilaiTukarDollar = 0.000065;
        double expectedDollar = nilaiRupiah * nilaiTukarDollar;
        double actualDollar = KonversiUang.konversiUang(nilaiRupiah, 1);
        assertEquals(expectedDollar, actualDollar, nilaiToleransi);

        // Uji konversi ke Yuan China
        double nilaiTukarYuan = 0.00046;
        double expectedYuan = nilaiRupiah * nilaiTukarYuan;
        double actualYuan = KonversiUang.konversiUang(nilaiRupiah, 2);
        assertEquals(expectedYuan, actualYuan, nilaiToleransi);

        // Uji konversi ke Yen Jepang
        double nilaiTukarYen = 0.0095;
        double expectedYen = nilaiRupiah * nilaiTukarYen;
        double actualYen = KonversiUang.konversiUang(nilaiRupiah, 3);
        assertEquals(expectedYen, actualYen, nilaiToleransi);

        // Uji konversi ke Riyal Arab Saudi
        double nilaiTukarRiyal = 0.00024;
        double expectedRiyal = nilaiRupiah * nilaiTukarRiyal;
        double actualRiyal = KonversiUang.konversiUang(nilaiRupiah, 4);
        assertEquals(expectedRiyal, actualRiyal, nilaiToleransi);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInputPilihanMataUangInvalid() {
        // Uji ketika pengguna memasukkan pilihan mata uang yang tidak valid
        // Harapannya adalah metode akan melempar IllegalArgumentException
        double nilaiRupiah = 100000;
        KonversiUang.konversiUang(nilaiRupiah, 5);
    }
}