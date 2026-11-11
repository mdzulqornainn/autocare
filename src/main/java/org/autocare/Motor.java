package org.autocare;

public class Motor extends Kendaraan {
    private String jenisTransmisi;

    public Motor(String platNomor, String merk, String pemilik, String jenisTransmisi) {
        super(platNomor, merk, pemilik);
        this.jenisTransmisi = jenisTransmisi;
    }

    public String getJenisTransmisi() { return jenisTransmisi; }
    public void setJenisTransmisi(String jenisTransmisi) { this.jenisTransmisi = jenisTransmisi; }

    @Override
    public void tampilkanInfo() {
        System.out.print("[MOTOR] ");
        super.tampilkanInfo();
        System.out.println("        Transmisi: " + jenisTransmisi);
    }
}
