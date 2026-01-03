public class Customer {
    public int id;
    public String adSoyad;
    public String telefon;

    public Customer(int id, String adSoyad, String telefon) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.telefon = telefon;
    }

    // Müşteri bilgisini txt formatına çevir
    public String dosyayaCevir() {
        return id + "," + adSoyad + "," + telefon;
    }
}