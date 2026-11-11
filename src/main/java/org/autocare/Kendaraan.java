package org.autocare;

public class Kendaraan {
    // Encapsulation
    private String platNomor;
    private String merk;
    private String pemilik;

    public Kendaraan(String platNomor, String merk, String pemilik) {
        this.platNomor = platNomor;
        this.merk = merk;
        this.pemilik = pemilik;
    }

    // Getter dan Setter
    public String getPlatNomor() { return platNomor; }
    public void setPlatNomor(String platNomor) { this.platNomor = platNomor; }

    public String getMerk() { return merk; }
    public void setMerk(String merk) { this.merk = merk; }

    public String getPemilik() { return pemilik; }
    public void setPemilik(String pemilik) { this.pemilik = pemilik; }

    public void tampilkanInfo() {
        System.out.println("Pemilik: " + pemilik + " | Plat: " + platNomor + " | Merk: " + merk);
    }
}