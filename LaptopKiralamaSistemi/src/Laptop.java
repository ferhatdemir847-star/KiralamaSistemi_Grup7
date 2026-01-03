public class Laptop {
    // Laptop özelliklerini burada tanımladım
    public int id;
    public String ad;
    public String marka;
    public double fiyat;
    public int stok;

    // Yeni laptop oluştururken bu kurucuyu (constructor) kullanıyoruz
    public Laptop(int id, String ad, String marka, double fiyat, int stok) {
        this.id = id;
        this.ad = ad;
        this.marka = marka;
        this.fiyat = fiyat;
        this.stok = stok;
    }

    // Kiralama olunca stoğu düşürüyoruz
    public void stokDusur() {
        if (stok > 0) {
            stok--;
        }
    }

    // İade gelince stoğu artırıyoruz
    public void stokArtir() {
        stok++;
    }

    // Dosyaya yazarken aralarına virgül koyarak string yapıyoruz
    public String dosyayaCevir() {
        return id + "," + ad + "," + marka + "," + fiyat + "," + stok;
    }
}