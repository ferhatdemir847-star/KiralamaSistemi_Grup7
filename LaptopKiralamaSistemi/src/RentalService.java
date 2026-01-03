import java.util.*;

public class RentalService {
    private FileManager dosyaYoneticisi = FileManager.getNesne();

    // Laptop Ekleme
    public void laptopEkle(String ad, String marka, double fiyat, int stok) {
        int id = dosyaYoneticisi.yeniIdVer("laptops.txt");
        Laptop yeniLaptop = new Laptop(id, ad, marka, fiyat, stok);

        List<String> liste = dosyaYoneticisi.dosyaOku("laptops.txt");
        liste.add(yeniLaptop.dosyayaCevir());
        dosyaYoneticisi.dosyaYaz("laptops.txt", liste);
        System.out.println("Laptop sisteme eklendi. ID: " + id);
    }

    // Müşteri Ekleme
    public void musteriEkle(String ad, String tel) {
        int id = dosyaYoneticisi.yeniIdVer("customers.txt");
        Customer musteri = new Customer(id, ad, tel);

        List<String> liste = dosyaYoneticisi.dosyaOku("customers.txt");
        liste.add(musteri.dosyayaCevir());
        dosyaYoneticisi.dosyaYaz("customers.txt", liste);
        System.out.println("Müşteri başarıyla kaydedildi.");
    }

    // Kiralama İşlemi (Stok kontrolü burada yapılıyor)
    public void laptopKirala(int mId, int lId, int gun) {
        List<Laptop> laptoplar = laptopListesiGetir();

        for (Laptop lp : laptoplar) {
            if (lp.id == lId) {
                if (lp.stok > 0) { // Stok var mı?
                    double tutar = lp.fiyat * gun;
                    lp.stokDusur(); // Stoğu azalttık

                    int kiraId = dosyaYoneticisi.yeniIdVer("rentals.txt");
                    Rental kiralama = new Rental(kiraId, mId, lId, gun, tutar, "AKTIF");

                    // Dosyaları güncelliyoruz
                    laptoplariKaydet(laptoplar);
                    kiralamaKaydet(kiralama);
                    System.out.println("Kiralama Yapıldı! Toplam Tutar: " + tutar + " TL");
                } else {
                    System.out.println("HATA: Stok yetersiz!");
                }
                return;
            }
        }
        System.out.println("HATA: Laptop bulunamadı!");
    }

    // İade İşlemi
    public void iadeAl(int kId) {
        List<Rental> kiralar = kiralamaListesiGetir();
        List<Laptop> laptoplar = laptopListesiGetir();

        for (Rental r : kiralar) {
            if (r.id == kId && r.durum.equals("AKTIF")) {
                r.durum = "IADE"; // Durumu güncelle

                // İlgili laptopun stoğunu geri artır
                for (Laptop lp : laptoplar) {
                    if (lp.id == r.laptopId) lp.stokArtir();
                }

                kiralamalariKaydet(kiralar);
                laptoplariKaydet(laptoplar);
                System.out.println("İade alındı, stok güncellendi.");
                return;
            }
        }
        System.out.println("HATA: Kiralama bulunamadı veya zaten iade edilmiş.");
    }

    // Dosyadan nesne listesine çeviren yardımcı metodlar
    public List<Laptop> laptopListesiGetir() {
        List<Laptop> liste = new ArrayList<>();
        for (String satir : dosyaYoneticisi.dosyaOku("laptops.txt")) {
            String[] p = satir.split(",");
            liste.add(new Laptop(Integer.parseInt(p[0]), p[1], p[2], Double.parseDouble(p[3]), Integer.parseInt(p[4])));
        }
        return liste;
    }

    public List<Customer> musteriListesiGetir() {
        List<Customer> liste = new ArrayList<>();
        for (String satir : dosyaYoneticisi.dosyaOku("customers.txt")) {
            String[] p = satir.split(",");
            liste.add(new Customer(Integer.parseInt(p[0]), p[1], p[2]));
        }
        return liste;
    }

    public List<Rental> kiralamaListesiGetir() {
        List<Rental> liste = new ArrayList<>();
        for (String satir : dosyaYoneticisi.dosyaOku("rentals.txt")) {
            String[] p = satir.split(",");
            liste.add(new Rental(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]), Double.parseDouble(p[4]), p[5]));
        }
        return liste;
    }

    // Kayıt metodları (Private)
    private void laptoplariKaydet(List<Laptop> liste) {
        List<String> satirlar = new ArrayList<>();
        for (Laptop lp : liste) satirlar.add(lp.dosyayaCevir());
        dosyaYoneticisi.dosyaYaz("laptops.txt", satirlar);
    }

    private void kiralamaKaydet(Rental r) {
        List<String> satirlar = dosyaYoneticisi.dosyaOku("rentals.txt");
        satirlar.add(r.dosyayaCevir());
        dosyaYoneticisi.dosyaYaz("rentals.txt", satirlar);
    }

    private void kiralamalariKaydet(List<Rental> liste) {
        List<String> satirlar = new ArrayList<>();
        for (Rental r : liste) satirlar.add(r.dosyayaCevir());
        dosyaYoneticisi.dosyaYaz("rentals.txt", satirlar);
    }
}