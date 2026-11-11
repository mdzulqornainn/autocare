package org.autocare;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
        boolean isRunning = true;

        System.out.println("=== Sistem Manajemen AutoCare ===");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Mobil");
            System.out.println("2. Tambah Motor");
            System.out.println("3. Tampilkan Daftar Kendaraan");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi (1-4): ");

            int opsi = input.nextInt();
            input.nextLine();

            switch (opsi) {
                case 1:
                    System.out.print("Plat Nomor Mobil: ");
                    String platMobil = input.nextLine();
                    System.out.print("Merk Mobil: ");
                    String merkMobil = input.nextLine();
                    System.out.print("Nama Pemilik: ");
                    String pemilikMobil = input.nextLine();
                    System.out.print("Jumlah Pintu: ");
                    int pintu = input.nextInt();

                    Mobil mobilBaru = new Mobil(platMobil, merkMobil, pemilikMobil, pintu);
                    daftarKendaraan.add(mobilBaru);
                    System.out.println(">> Mobil berhasil diregistrasi!");
                    break;

                case 2:
                    System.out.print("Plat Nomor Motor: ");
                    String platMotor = input.nextLine();
                    System.out.print("Merk Motor: ");
                    String merkMotor = input.nextLine();
                    System.out.print("Nama Pemilik: ");
                    String pemilikMotor = input.nextLine();
                    System.out.print("Jenis Transmisi (Manual/Matic): ");
                    String transmisi = input.nextLine();

                    Motor motorBaru = new Motor(platMotor, merkMotor, pemilikMotor, transmisi);
                    daftarKendaraan.add(motorBaru);
                    System.out.println(">> Motor berhasil diregistrasi!");
                    break;

                case 3:
                    System.out.println("\n--- Daftar Kendaraan Terdaftar ---");
                    if (daftarKendaraan.isEmpty()) {
                        System.out.println("Belum ada data kendaraan.");
                    } else {
                        for (Kendaraan k : daftarKendaraan) {
                            k.tampilkanInfo();
                        }
                    }
                    break;

                case 4:
                    isRunning = false;
                    System.out.println("Keluar dari sistem AutoCare. Terima kasih!");
                    break;

                default:
                    System.out.println("Opsi tidak valid!");
            }
        }
        input.close();
    }
}
