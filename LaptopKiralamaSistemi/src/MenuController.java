import java.util.Scanner;

public class MenuController {
    private RentalService servis = new RentalService();
    private Scanner tarayici = new Scanner(System.in);

    public void menuyuBaslat() {
        while (true) {
            System.out.println("\n=== LAPTOP KİRALAMA SİSTEMİ ===");
            System.out.println("1 - Laptop Ekle");
            System.out.println("2 - Laptopları Listele");
            System.out.println("3 - Müşteri Ekle");
            System.out.println("4 - Müşterileri Listele");
            System.out.println("5 - Kiralama Yap");
            System.out.println("6 - Kiralamaları Gör");
            System.out.println("7 - İade Et");
            System.out.println("0 - Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = tarayici.nextInt();
            tarayici.nextLine(); // Enter karakterini temizle

            if (secim == 1) {
                System.out.print("Laptop Adı: "); String ad = tarayici.nextLine();
                System.out.print("Marka/Model: "); String marka = tarayici.nextLine();
                System.out.print("Günlük Fiyat: "); double fiyat = tarayici.nextDouble();
                System.out.print("Stok: "); int stok = tarayici.nextInt();
                servis.laptopEkle(ad, marka, fiyat, stok);

            } else if (secim == 2) {
                System.out.println("\n--- LAPTOP LİSTESİ ---");
                System.out.println("ID | AD | MARKA | FİYAT | STOK");
                for (Laptop lp : servis.laptopListesiGetir()) {
                    System.out.println(lp.id + " | " + lp.ad + " | " + lp.marka + " | " + lp.fiyat + " | " + lp.stok);
                }

            } else if (secim == 3) {
                System.out.print("Müşteri Adı: "); String ad = tarayici.nextLine();
                System.out.print("Telefon: "); String tel = tarayici.nextLine();
                servis.musteriEkle(ad, tel);

            } else if (secim == 4) {
                System.out.println("\n--- MÜŞTERİ LİSTESİ ---");
                for (Customer c : servis.musteriListesiGetir()) {
                    System.out.println(c.id + " - " + c.adSoyad);
                }

            } else if (secim == 5) {
                System.out.print("Müşteri ID: "); int mId = tarayici.nextInt();
                System.out.print("Laptop ID: "); int lId = tarayici.nextInt();
                System.out.print("Kaç Gün: "); int gun = tarayici.nextInt();
                servis.laptopKirala(mId, lId, gun);

            } else if (secim == 6) {
                System.out.println("\n--- KİRALAMA GEÇMİŞİ ---");
                for (Rental r : servis.kiralamaListesiGetir()) {
                    System.out.println("Kira ID: " + r.id + " | Durum: " + r.durum + " | Tutar: " + r.toplamTutar);
                }

            } else if (secim == 7) {
                System.out.print("İade edilecek Kira ID: ");
                servis.iadeAl(tarayici.nextInt());

            } else if (secim == 0) {
                System.out.println("Çıkış yapılıyor...");
                break;
            }
        }
    }
}