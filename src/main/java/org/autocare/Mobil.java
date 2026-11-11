package org.autocare;


public class Mobil extends Kendaraan {
    private int jumlahPintu;

    public Mobil(String platNomor, String merk, String pemilik, int jumlahPintu) {
        super(platNomor, merk, pemilik);
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() { return jumlahPintu; }
    public void setJumlahPintu(int jumlahPintu) { this.jumlahPintu = jumlahPintu; }

    @Override
    public String getInfo() {
        return "[MOBIL] " + super.getInfo() + " | Jumlah Pintu: " + jumlahPintu;
    }
}