##  Proje Mimarisi ve Dosya Yapısı

Proje, "Yazılım Mühendisliği" prensipleri ve "Katmanlı Mimari" yaklaşımına uygun olarak, her biri belirli bir sorumluluğu üstlenen sınıflardan oluşmaktadır.

```
LaptopKiralamaSistemi/
├── src/
│   ├── Main.java                # Ana başlatıcı (Entry Point)
│   ├── MenuController.java      # Kullanıcı Arayüzü ve Menü Yönetimi
│   ├── RentalService.java       # İş Mantığı ve Kiralama Operasyonları
│   ├── FileManager.java         # Dosya İşlemleri ve Veri Kalıcılığı
│   ├── Customer.java           # Müşteri Veri Modeli
│   ├── Laptop.java             # Laptop Envanter ve Stok Yönetimi Modeli
│   └── Rental.java             # Kiralama Kaydı ve İşlem Takibi Modeli
│
├── data/                        # Veri Depolama (Otomatik Oluşur)
│   ├── customers.txt           # Müşteri Kayıtları (CSV Format)
│   ├── laptops.txt             # Laptop Envanteri (CSV Format)
│   └── rentals.txt             # Kiralama Geçmişi (CSV Format)
│
├── docs/                       # Proje Dokümantasyonu
│   ├── ANALIZ_RAPORU.md        # Sistem Analiz ve Gereksinimler
│   ├── TASARIM_RAPORU.md       # Teknik Tasarım ve Mimari
│   └── FINAL_RAPORU.md         # Proje Sonuç ve Değerlendirme
│
└── README.md                   # Bu Dosya
```

##  Temel Özellikler

### 1. Modüler Tasarım ve Katmanlı Mimari (OOP Principles)

Spagetti kod yapısından kaçınılarak, her sınıfın tek bir sorumluluğu üstlendiği (Single Responsibility Principle) bir yapı kurulmuştur:

- **Veri Katmanı (Entity Layer):** `Customer.java`, `Laptop.java`, `Rental.java` - Temel veri modelleri
- **Veri Erişim Katmanı (Data Access Layer):** `FileManager.java` - Tüm dosya I/O işlemleri burada izole edilmiştir (Singleton Pattern)
- **İş Mantığı Katmanı (Business Layer):** `RentalService.java` - Kiralama, iade, stok yönetimi algoritmaları
- **Sunum Katmanı (Presentation Layer):** `MenuController.java` - Kullanıcı etkileşimi ve konsol arayüzü

### 2. Akıllı Stok ve Kiralama Yönetimi

- **Otomatik Stok Kontrolü:** Kiralama sırasında gerçek zamanlı stok kontrolü
- **Senkronize İşlemler:** Kiralama ve iade işlemlerinde veri bütünlüğü garantisi
- **Durum Takibi:** Kiralamalar `AKTIF` → `IADE` durumları arasında izlenebilir şekilde yönetilir
- **Otomatik ID Yönetimi:** Benzersiz ID'ler dosya tabanlı otomatik üretim ile sağlanır

### 3. Kritik Kontroller ve Validasyon

- **Stok Validasyonu:** Negatif stok oluşması engellenir (`stokDusur()` metodunda kontrol)
- **Referans Bütünlüğü:** Geçersiz müşteri/laptop ID ile işlem engellenir
- **Veri Tutarlılığı:** Tüm dosya işlemleri atomik olarak gerçekleştirilir
- **Hata Yönetimi:** `try-catch` blokları ile sistem çökmeleri önlenir

## Kurulum ve Çalıştırma

Projeyi yerel makinenizde çalıştırmak için:

### 1. Projeyi Klonlayın
```bash
git clone https://github.com/kullaniciadi/LaptopKiralamaSistemi.git
cd LaptopKiralamaSistemi
```

### 2. Java Dosyalarını Derleyin
```bash
javac src/*.java
```

### 3. Uygulamayı Başlatın
```bash
java src/Main
```

### Alternatif: IDE ile Çalıştırma
- IntelliJ IDEA, Eclipse veya VS Code ile projeyi açın
- `src/Main.java` dosyasını ana sınıf olarak belirleyin
- Projeyi çalıştırın

##  Sistem Gereksinimleri

- **Java JDK 8 veya üzeri** (Java SE uyumlu)
- **Minimum 512 MB RAM**
- **100 MB boş disk alanı**
- **Windows / Linux / macOS işletim sistemi**

##  Kullanım Kılavuzu

### Ana Menü Yapısı
```
=== LAPTOP KİRALAMA SİSTEMİ ===
1 - Laptop Ekle
2 - Laptopları Listele
3 - Müşteri Ekle
4 - Müşterileri Listele
5 - Kiralama Yap
6 - Kiralamaları Gör
7 - İade Et
0 - Çıkış
Seçiminiz: 
```

### Hızlı Başlangıç Senaryosu

1. **Müşteri Kaydı:** Menüden `3` seçeneği ile yeni müşteri ekleyin
2. **Laptop Ekleme:** Menüden `1` seçeneği ile laptop envanterine ekleyin
3. **Kiralama İşlemi:** Menüden `5` seçeneği ile kiralama yapın
4. **İzleme:** Menüden `2`, `4`, `6` seçenekleri ile listeleri görüntüleyin
5. **İade:** Menüden `7` seçeneği ile kiralama iadesi alın

### Veri Dosyaları Formatı

**customers.txt:**
```
ID,Ad Soyad,Telefon
1,Ali Yılmaz,5551234567
```

**laptops.txt:**
```
ID,Ad,Marka,Fiyat,Stok
1,MacBook Air,Apple,150.0,5
```

**rentals.txt:**
```
ID,MusteriID,LaptopID,Gun,Tutar,Durum
1,1,1,3,450.0,AKTIF
```

##  Teknik Özellikler

### Tasarım Desenleri
-  **Singleton Pattern:** `FileManager.java` - Tekil dosya yöneticisi
-  **DTO Pattern:** Veri transfer nesneleri
-  **Layered Architecture:** Katmanlı mimari
-  **Separation of Concerns:** Sorumluluk ayrımı

### Performans Metrikleri
- **Başlangıç Süresi:** < 3 saniye
- **İşlem Yanıt Süresi:** < 2 saniye
- **Maksimum Kayıt:** 1000+ kayıt destekli
- **Bellek Kullanımı:** < 50 MB

### Güvenlik Özellikleri
- Veri dosyaları düz metin (CSV) formatında
- Temel girdi validasyonu
- Dosya bozulması durumunda boş liste dönüşü
- Hata mesajları kullanıcı dostu

##  Proje Dokümantasyonu

Projenin teknik detayları, analiz ve tasarım süreçleri `docs/` klasöründe sunulmuştur:

- **ANALIZ_RAPORU.md:** Sistem gereksinimleri, kullanım senaryoları ve iş kuralları
- **TASARIM_RAPORU.md:** Mimari yapı, sınıf diyagramları ve tasarım kararları
- **FINAL_RAPORU.md:** Proje sonuçları, test raporları ve değerlendirmeler

##  Geliştirme ve Katkı

### Geliştirme Ortamı Kurulumu
```bash
# Projeyi forklayın
git fork https://github.com/kullaniciadi/LaptopKiralamaSistemi.git

# Geliştirme branch'i oluşturun
git checkout -b feature/yeni-ozellik

# Değişiklikleri commit edin
git commit -m "Yeni özellik eklendi"

# Pull Request gönderin
```

### Önerilen Geliştirmeler
1. **Veritabanı Entegrasyonu:** SQLite veya MySQL desteği
2. **Web Arayüzü:** Spring Boot + React entegrasyonu
3. **Raporlama Modülü:** PDF/Excel çıktıları
4. **Çoklu Dil Desteği:** İngilizce arayüz
5. **Unit Testler:** JUnit test kapsamı

##  Geliştiriciler

**[Ferhat Demir, Sabri Kaçar, İdris Şeyhun ]** - Yazılım Mühendisliği Öğrencileri/Geliştirileri

Bu proje **Yazılım Mühendisliği** prensipleri ve **Nesne Yönelimli Programlama** dersi kapsamında geliştirilmiştir.

##  İletişim ve Destek

- **GitHub:** [ferhatdemir847-star](https://github.com/ferhatdemir847-star)
- **E-posta:**ferhat.demir847@gmail.com
- **Proje Linki:** [LaptopKiralamaSistemi](https://github.com/ferhatdemir847-star/KiralamaSistemi_Grup7)

---
