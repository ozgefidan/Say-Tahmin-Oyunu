import java.util.*;  // Scanner, Random, Timer sınıfları için

// ===================== OYUN AYARLARI =====================
class OyunAyar {
    int min;                 // minimum değer
    int maks;                // maksimum değer
    int maxDeneme;           // maksimum deneme hakkı
    int zamanLimiti;         // saniye cinsinden zaman limiti (0 ise kapalı)
    
    public OyunAyar(int min, int maks, int maxDeneme, int zamanLimiti) {
        this.min = min;
        this.maks = maks;
        this.maxDeneme = maxDeneme;
        this.zamanLimiti = zamanLimiti;
    }

    // Süre sınırlaması aktif mi?
    public boolean zamanAktif() {
        return zamanLimiti > 0;
    }
}

// ===================== SAYI TAHMİN OYUNU SINIFI =====================
public class SayiTahminOyunu {
    private int gizliSayi;          // Rastgele seçilen gizli sayı
    private int kalanDeneme;        // Kullanıcının kalan deneme hakkı
    private boolean kazandi;        // Oyuncu kazandı mı
    private boolean bitti;          // Oyun bitti mi
    private long baslangicZamani;   // Süreli mod için başlangıç zamanı
    private OyunAyar ayar;          // Oyun ayarları
    private Scanner girdi;          // Kullanıcıdan girdi almak için
    private List<Integer> denemeler; // Yapılan tahminleri tutar

    // ===================== KURULUCU =====================
    public SayiTahminOyunu(OyunAyar ayar) {
        this.ayar = ayar;
        this.gizliSayi = new Random().nextInt(ayar.maks - ayar.min + 1) + ayar.min;
        this.kalanDeneme = ayar.maxDeneme;
        this.kazandi = false;
        this.bitti = false;
        this.girdi = new Scanner(System.in);
        this.denemeler = new ArrayList<>();
        this.baslangicZamani = System.currentTimeMillis();
    }

    // ===================== ANA OYUN DÖNGÜSÜ =====================
    public void baslat() {
        System.out.println("=== 🎯 SAYI TAHMİN OYUNU 🎯 ===");
        System.out.println("Aralık: " + ayar.min + " - " + ayar.maks);
        System.out.println("Toplam deneme hakkı: " + ayar.maxDeneme);
        if (ayar.zamanAktif()) {
            System.out.println("Zaman limiti: " + ayar.zamanLimiti + " saniye");
        }
        System.out.println("=======================================");

        while (!bitti) {
            if (ayar.zamanAktif() && zamanBittiMi()) {
                System.out.println("⏰ Süre doldu! Gizli sayı: " + gizliSayi);
                bitti = true;
                break;
            }

            System.out.println("\nKalan deneme: " + kalanDeneme + 
                (ayar.zamanAktif() ? " | Kalan süre: " + kalanSaniye() + " sn" : ""));
            System.out.print("Tahminini gir (" + ayar.min + "-" + ayar.maks + "): ");

            String giris = girdi.nextLine().trim();

            if (giris.isEmpty()) {
                System.out.println("⚠️ Boş giriş yaptın, tekrar dene!");
                continue;
            }

            int tahmin;
            try {
                tahmin = Integer.parseInt(giris);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Lütfen sayısal bir değer gir!");
                continue;
            }

            if (tahmin < ayar.min || tahmin > ayar.maks) {
                System.out.println("⚠️ Aralık dışı değer girdin!");
                continue;
            }

            denemeler.add(tahmin);
            kalanDeneme--;

            if (tahmin < gizliSayi) {
                System.out.println("⬆️ Daha büyük bir sayı dene!");
            } else if (tahmin > gizliSayi) {
                System.out.println("⬇️ Daha küçük bir sayı dene!");
            } else {
                kazandi = true;
                bitti = true;
                System.out.println("🎉 Tebrikler! Sayıyı doğru bildin! (" + gizliSayi + ")");
                System.out.println("Toplam deneme: " + denemeler.size());
                break;
            }

            if (kalanDeneme == 0) {
                bitti = true;
                System.out.println("😢 Deneme hakkın bitti! Gizli sayı: " + gizliSayi);
            }
        }

        oyunOzeti();
    }

    // ===================== KALAN ZAMAN HESAPLAMA =====================
    private long kalanSaniye() {
        long gecen = (System.currentTimeMillis() - baslangicZamani) / 1000;
        return Math.max(0, ayar.zamanLimiti - gecen);
    }

    private boolean zamanBittiMi() {
        return kalanSaniye() <= 0;
    }

    // ===================== OYUN SONU RAPOR =====================
    private void oyunOzeti() {
        System.out.println("\n==== 📊 OYUN ÖZETİ 📊 ====");
        System.out.println("Hedef sayı: " + gizliSayi);
        System.out.println("Toplam deneme: " + denemeler.size());
        System.out.println("Tahminler: " + denemeler);
        if (kazandi) {
            System.out.println("Durum: ✅ Kazandın!");
        } else {
            System.out.println("Durum: ❌ Kaybettin!");
        }
        System.out.println("===========================");
    }

    // ===================== PROGRAM BAŞLATMA NOKTASI =====================
    public static void main(String[] args) {
        Scanner giris = new Scanner(System.in);

        System.out.println("=== SAYI TAHMİN OYUNU AYARLARI ===");
        System.out.print("Aralık seç: (1) 1-100  (2) 1-1000 : ");
        int secim = giris.nextInt();
        int min = 1;
        int maks = (secim == 2) ? 1000 : 100;

        System.out.print("Maksimum deneme hakkı (varsayılan 7): ");
        int deneme = giris.nextInt();
        if (deneme <= 0) deneme = 7;

        System.out.print("Zaman limiti kullanılsın mı? (e/h): ");
        char zamanSecim = giris.next().toLowerCase().charAt(0);
        int zaman = 0;
        if (zamanSecim == 'e') {
            System.out.print("Toplam saniye (varsayılan 60): ");
            zaman = giris.nextInt();
            if (zaman <= 0) zaman = 60;
        }

        giris.nextLine(); // tamponu temizle

        OyunAyar ayar = new OyunAyar(min, maks, deneme, zaman);
        SayiTahminOyunu oyun = new SayiTahminOyunu(ayar);
        oyun.baslat();
        giris.close();
    }
}