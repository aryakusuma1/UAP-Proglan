# Program Konversi Uang

Program ini merupakan aplikasi sederhana untuk melakukan konversi mata uang. Pengguna dapat memilih mata uang yang ingin dikonversi, memasukkan jumlah uang dalam Rupiah, dan melihat riwayat konversi.

## Fitur Utama

1. **Konversi Uang:** Melakukan konversi mata uang dari Rupiah ke mata uang lain.
2. **Tampilkan Riwayat Konversi:** Menampilkan riwayat konversi yang telah dilakukan.
3. **Keluar:** Keluar dari program.

## Mata Uang yang Dapat Dikonversi

1. Dollar Amerika
2. Yuan China
3. Yen Jepang
4. Riyal Arab Saudi

## Batasan Konversi

- Minimal konversi adalah Rp100,000.
- Maksimal konversi adalah Rp1,000,000.

## Cara Menggunakan

1. Pilih menu sesuai dengan opsi yang diinginkan.
2. Ikuti petunjuk untuk melakukan konversi uang.
3. Lihat riwayat konversi untuk melihat hasil konversi sebelumnya.

## Exception Handling

Program ini menggunakan exception handling untuk mengatasi beberapa situasi yang mungkin terjadi:

- `InputMismatchException:` Menangani kesalahan saat pengguna memasukkan input yang tidak sesuai dengan yang diharapkan, seperti memasukkan huruf saat seharusnya memasukkan angka.

- `IllegalArgumentException:` Dilempar saat terjadi kesalahan dalam pemilihan mata uang atau jika jumlah uang yang dimasukkan di luar batas yang ditentukan.

- `FileNotFoundException:` Menangani kesalahan saat mencoba membaca riwayat konversi dari file yang tidak ditemukan.

- `IOException:` Menangani kesalahan umum dalam proses pembacaan atau penulisan ke file, seperti ketika gagal menyimpan riwayat konversi.

## Catatan

- Riwayat konversi disimpan dalam file "riwayat_konversi.txt".
- Jika belum ada riwayat konversi, pesan "Maaf, Belum ada Riwayat Konversi. Silahkan melakukan Konversi." akan ditampilkan saat memilih menu untuk menampilkan riwayat.

## Kontribusi

Jika Anda menemui bug atau memiliki saran perbaikan, silakan buat *issue* atau *pull request*.

Terima kasih!
