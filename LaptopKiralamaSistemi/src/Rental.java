public class Rental {
    public int id;
    public int musteriId;
    public int laptopId;
    public int gun;
    public double toplamTutar;
    public String durum; // "AKTIF" veya "IADE"

    public Rental(int id, int musteriId, int laptopId, int gun, double toplamTutar, String durum) {
        this.id = id;
        this.musteriId = musteriId;
        this.laptopId = laptopId;
        this.gun = gun;
        this.toplamTutar = toplamTutar;
        this.durum = durum;
    }

    // Kiralama bilgisini txt formatına çevir
    public String dosyayaCevir() {
        return id + "," + musteriId + "," + laptopId + "," + gun + "," + toplamTutar + "," + durum;
    }
}