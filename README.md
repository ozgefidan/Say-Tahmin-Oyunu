# Sayı Tahmin Oyunu (Java)

Bu proje, Java dili kullanılarak geliştirilmiş **konsol tabanlı bir sayı tahmin oyunudur**.  
Oyuncu belirlenen aralıkta gizli bir sayıyı, sınırlı deneme hakkı ve isteğe bağlı zaman limiti içinde tahmin etmeye çalışır.

## Proje Özellikleri
- Ayarlanabilir sayı aralığı (1–100 / 1–1000)
- Kullanıcı tarafından belirlenen deneme hakkı
- Opsiyonel zaman sınırlı oyun modu
- Girilen tahminlerin kayıt altına alınması
- Oyun sonunda detaylı özet raporu
- Hatalı giriş kontrolleri (boş, aralık dışı, sayısal olmayan)

## Kullanılan Teknolojiler
- Java
- Nesne Yönelimli Programlama (OOP)
- Java Collections (List)
- Scanner ve Random sınıfları
- Zaman kontrolü (System.currentTimeMillis)

## Oyun Akışı
- Oyun başında kullanıcıdan ayarlar alınır
- Rastgele bir gizli sayı oluşturulur
- Oyuncu her tahminde yönlendirilir (daha büyük / daha küçük)
- Süre veya deneme hakkı biterse oyun sona erer
- Oyun sonunda tahmin geçmişi ve sonuç gösterilir

## Çalıştırma
javac SayiTahminOyunu.java  
java SayiTahminOyunu  

## Not
Bu proje eğitim amaçlı geliştirilmiştir ve Java temel kavramları ile OOP mantığını pekiştirmeyi amaçlar.

## Geliştirici
Özge Fidan Yıldız – Yazılım Mühendisliği Öğrencisi
