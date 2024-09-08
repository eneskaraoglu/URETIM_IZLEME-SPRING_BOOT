Proje Tanımı:
Bu proje, bir üretim hattında gerçekleşen işlemleri izlemek ve raporlamak için tasarlanmış bir sistemdir. Üretim hattındaki makinelerin durumu, üretim sürecinin her aşamasında toplanan verilerle takip edilir ve bu veriler MongoDB'de saklanır. Sistem, makinelerin çalışma süresi, arıza durumu, üretilen ürün sayısı gibi bilgileri toplayarak kullanıcıya gerçek zamanlı raporlar sunar.
Ana Özellikler:
1.Makine Durumu İzleme:
Her makinenin durumu (çalışıyor, durdu, arızalı) izlenir. 
Veriler gerçek zamanlı olarak MongoDB'ye kaydedilir.

2.Üretim Verisi Toplama:
Her bir üretim hattında üretilen ürünlerin sayısı kaydedilir.
Üretim süreçleri ve süreleri izlenir.

3.Raporlama:
Günlük, haftalık ve aylık üretim raporları oluşturulur.
Makine arızalarının sıklığı ve süresi hakkında raporlar sunulur.
Üretim verimliliği raporları.

4.Kullanıcı Yönetimi:
Farklı kullanıcı rolleri (yönetici, operatör, bakım personeli) oluşturulabilir.
Her kullanıcının yetkilerine göre sistemdeki erişim düzeyleri belirlenir.

5.Gerçek Zamanlı Uyarılar:
Makine arızaları veya üretim hattındaki durmalar gibi kritik durumlar için gerçek zamanlı uyarılar gönderilir.

Teknolojik Yığın:
Backend: Java Spring Boot
Veritabanı: MongoDB (veri esnekliği ve ölçeklenebilirliği için)
Frontend: Thymeleaf 
Gerçek Zamanlı Veri: WebSockets veya RabbitMQ gibi bir mesajlaşma kuyruğu sistemi
Güvenlik: Spring Security

Adımlar:
Proje Kurulumu:

Spring Boot projesini başlatın ve MongoDB ile bağlantısını kurun.

2. Veri Modelleme:
MongoDB'de üretim hattı, makine, ürün ve kullanıcı gibi ana koleksiyonları tasarlayın.

3. Servis ve Controller Katmanları:
Üretim hattı verilerinin toplanması ve raporlanması için servisler oluşturun.
Kullanıcı giriş, kayıt ve yetkilendirme işlemleri için gerekli controller'ları yazın.

4. Gerçek Zamanlı İzleme:
Makine durumu ve üretim verilerini gerçek zamanlı olarak izlemek için gerekli altyapıyı kurun.
WebSockets veya bir mesajlaşma kuyruğu sistemi kullanarak uyarıları yönetin.

5. Raporlama Modülü:
Veritabanında toplanan verileri analiz edip raporlar oluşturacak bir modül geliştirin.

Bu proje, üretim sanayisinde verimlilik ve operasyonel iyileştirmeler için faydalı olabilecek bir sistem sunar. Aynı zamanda, Spring Boot ve MongoDB ile tam anlamıyla entegre bir uygulama geliştirme fırsatı sağlar.

-----------------------------

test için ;
http://localhost:8080/test/makineListesi



http://localhost:8080/
