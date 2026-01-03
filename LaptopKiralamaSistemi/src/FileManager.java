import java.io.*;
import java.util.*;

public class FileManager {
    // Singleton: Uygulamada sadece bir tane dosya yöneticisi olsun istedim
    private static FileManager nesne;

    private FileManager() {
        // Private constructor
    }

    public static FileManager getNesne() {
        if (nesne == null) {
            nesne = new FileManager();
        }
        return nesne;
    }

    // Yeni kayıt için otomatik ID üretimi (Son ID + 1)
    public int yeniIdVer(String dosyaAdi) {
        List<String> satirlar = dosyaOku(dosyaAdi);
        if (satirlar.isEmpty()) return 1;

        // Son satırı alıp virgülden önceki ID'yi okuyoruz
        String sonSatir = satirlar.get(satirlar.size() - 1);
        return Integer.parseInt(sonSatir.split(",")[0]) + 1;
    }

    // Dosyayı okuyup liste olarak dönen metod
    public List<String> dosyaOku(String dosyaAdi) {
        List<String> liste = new ArrayList<>();
        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = okuyucu.readLine()) != null) {
                liste.add(satir);
            }
        } catch (IOException e) {
            // Dosya yoksa hata vermesin, boş liste dönsün
        }
        return liste;
    }

    // Listeyi dosyaya yazan metod
    public void dosyaYaz(String dosyaAdi, List<String> veriListesi) {
        try (PrintWriter yazici = new PrintWriter(new FileWriter(dosyaAdi))) {
            for (String veri : veriListesi) {
                yazici.println(veri);
            }
        } catch (IOException e) {
            System.out.println("Dosya yazılırken hata oluştu: " + e.getMessage());
        }
    }
}