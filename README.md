<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->






<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/mtekbilisim">
    <img src="https://www.cioupdate.com.tr/wp-content/uploads/2020/06/poc.jpg" alt="Logo">
  </a>

<h3 align="center">Turkcell GoArena POC</h3>

  <p align="center">
    Java/Spring backed microservices architecture
    <br />
    <a href="http://www.mtekbilisim.com"><strong>MTek Bilişim A.Ş. »</strong></a>
    <br />
    <br />
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>İçerik</summary>
  <ol>
    <li>
      <a href="#about-the-project">Proje hakkında</a>
      <ul>
        <li><a href="#built-with">Mimari hakkında</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Başlangıç</a>
      <ul>
        <li><a href="#prerequisites">Gereksinimler</a></li>
        <li><a href="#installation">Kurulum</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Yol Haritası</a></li>
    <li><a href="#contributing">Katılım</a></li>
    <li><a href="#license">Lisans</a></li>
    <li><a href="#contact">İletişim</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Proje Hakkında

Turkcell bayileri ve bayi çalışanları arasında anlık paylaşımların oluşturulabileceği mini sosyal platform ile
bayi çalışanlarının aylık hedeflerini gözlemlemelerine yardımcı olacak bir uygulama geliştirilmesi hedeflenmiştir. 

Amaçlar:
* Bayiler arası etkileşimi arttırmak.
* Bayiler arası ve/veya bayi çalışanları arasında rekabeti canlandırmak
* Güzel anların paylaşılarak çoğalmasını sağlamak
* Kişi ve TİM bazında hedeflere dair öngörü sağlamak

Elbette her kullanıcının ihtiyaçları değişken olabilir. Proje minimal düzeyde ortak paydada fayda sağlamayı hedeflemiştir.

Aşağıda uygulamayı geliştirirken kullandığımız araçlara dair bilgileri bulabilirsiniz.

### Mimari Hakkında

Bu bölümde oluşturduğumuz mikro servis mimarisi ve kullandığımız ana kütüphane, çatı ve 3ncü parti hizmetler hakkında bilgi mevcuttur.

* [Kotlin](https://kotlinlang.org/)
* [Java 15](https://www.oracle.com/tr/java/technologies/javase-jdk15-downloads.html)
* [Spring Framework](https://spring.io/)
* [Spring Data](https://spring.io/)
* [Spring Security](https://spring.io/)
* [Spring JPA](https://spring.io/)
* [Spring Cloud Gateway](https://spring.io/)
* [Spring Cloud Service Discovery Server](https://spring.io/)
* [Spring Cloud Service Discovery Client](https://spring.io/)
* [Spring OAuth](https://spring.io/)
* [Spring Okta](https://www.okta.com/)
* [Spring Okta OAuth](https://www.okta.com/)
* [Spring DOC Open API 3](https://springdoc.org/)
* [Hibernate](https://hibernate.org/)
* [Redis](https://redis.io/)
* [PostgreSql](https://www.postgresql.org/)

<br />
<p align="center">
  <a href="https://github.com/mtekbilisim">
    <img src="https://hmp.me/dkeb" alt="Logo">
  </a>
</p>

Mikro servis mimarisinin gereksinimi olan yapılar öncelikli olarak aşağıdaki servisler oluşturulmuştur.
* Servis kaşifi
* Geçiş kapısı
* Kimliklendirme ve Yetkilendirme servisi

Servis kaşifi servisi için Spring Cloud Netflix Eureka Server ve tüm diğer mikro servisler için ise Spring Cloud Netflix Eureka Client
kullanılmıştır.

Dökümantasyon ve hızlı test için springdoc kütüphanesi yardımı ile tüm etkileşim noktaları dökümante edilmiştir.

Merkezi kimliklendirme, yetkilendirme ve aynı zaman 3ncü parti entegrasyonlara örnek teşkil etmesi için Okta OAuth hizmetinden faydalanılmıştır.
Böylece servis-servis haberleşmesine örnek teşkil etmiştir.

**İşleyiş**

Sistemin işlemesini sağlayan tüm servisler çalışmaya başladıklarında kendilerini servis kaşifine bildirirler.
Servis kaşifi geçiş kapısına bu servislerin çalışır durumda olduğunu bildirir. Böylece geçiş kapısı servisi artık bu hizmetler için geçişe müsade edecektir.

Yoğunluk anında, yoğunluk yaşayan servis ya da servislerden ek örnekler oluşturulabilir. Sistem kaşif servisi ve geçiş kapısı servisleri sayesinde
otomatik olarak yük dağılımı yapacaktır. Üstelik servisin port ya da ip adresinin bir önemi yoktur. Tüm sistem otomatik çalışır.

İstemcilerden gelen tüm istekler öncelikle Geçiş kapısı servisine gelir. Geçiş kapısı servisi herhangi bir yetkilendirme 
ve kimliklendirme işlemi yapmaz. Dağıtık sistemlerin her birinin farklı sorumluluk alanı olacağı gerekçesi ile bu işi ili mikro servislere devreder.

Mikro servislerin her biri kendisine yönlendirilen isteği karşılar ve öncelikle oturum geçerliliği kontrolü yaparlar.
Eğer oturum geçerli ise söz konusu servisi işlevlerini yerine getirirler.

Temelde dağıtık mimarilerin ihtiyaç duyacakları pek çok kritik nokta POC gündeminde ele alınmıştır.

<!-- GETTING STARTED -->
## Başlangıç

Uygulamayı kendi tarafınızda kurabilmek ve çalıştırabilmek için aşağıdaki adımları takip etmeniz gereklidir.

### Gereksinimler

* Java 15 ( Gradle yapılandırma iş süreçleri için gereklidir )
  ```sh
  Open JDK 15 ya da Oracle JDK 15 SE
  ```
* Docker ( Tüm mimarı docker üzerine inşaa edilmiştir )
  ```sh
  Docker && Docker Compose
  ```
### Kurulum

1. Repoyu klonlayın
   ```sh
   git clone https://github.com/mtekbilisim/goArenaPoc-backend
   ```
2. dockerBuild.sh çalıştırılabilir dosyasını tetikleyin 
   ```sh
   ./dockerBuild.sh
   ```
Hepsi bu kadar! Aşağıdaki adresi ziyaret ederek tüm servislerin dökümantasyonuna ulaşabilirsiniz

```sh
   http://localhost:8080/swagger.html
   ```
Ayrıca kaşif sisteminin durumunu ve ayakta olan hizmetleri görmek için aşağıdaki adresi ziyaret edebilirsiniz.
```sh
   http://localhost:8761
   ```
PostgreSql veritabanına olaşmak isterseniz 5432 yerine 15432 numaralı portu kullanmalısınız.
```sh
   postgresql://localhost:15432/goArena
   ```

**Eğer tek bir servisi yeniden yapılandırma ihtiyacı doğarsa aşağıdaki örneği uygulayabilirsiniz.**

1. Servis klasörüne girin ve Docker imajı oluşturma işini tetikleyin
   ```sh
   cd file_service 
   ./gradlew bootBuildImage
   ```
2. Üst klasöre geri dönerek docker-compose yardımcısının servisinizi güncellemesini sağlayın

   ```sh
   cd .. 
   docker-compose up -d --remove-orphans
   ```

Oluşturulan servis ya da servisler otomatik olarak sisteme dahil olacaktır.

<!-- ROADMAP -->
## Yol Haritası

Kotlin dili sayesinde reactive uygulamaları gerçeklemek giderek daha kolay olmakta. Bu sebeple hibernate yerine r2dbc kullanarak
etkileşimi yüksek ve reaktif davranan gerçek zamanlı bir uygulama dönüştürmek mümkün. Kullanılan tüm anotasyonlar bu geliştirmenin
yapılması ihtimaline karşın dikkatle seçilmiş ve uygulamanın ilk geliştirme sürecinde r2dbc ile test edilmiştir.

Redis ile önbellekleme gündeme alınmış yapısı hazırlanmış ancak herhangi bir istek noktasına uygulanmamıştır.
Uygulamanın özellikle soğuk çalışma performasını gözlemlek adına bu adımdan vazgeçilmiştir. Arzu edilirse
hiç bir kod yazmadan sadece anotasyonları etkileştirerek redis önbelleğini devreye almak mümkündür.



<!-- CONTRIBUTING -->
## Katılım

Katılım projenin bir POC olması sebebi ile **kapatılmıştır**. Ancak projemizi beğendiyseniz paylaşıma açığız. Bu sebeple MIT lisansını tercih ettik.</br>
[![LinkedIn][linkedin-shield]][linkedin-url]
<!-- LICENSE -->
## Lisans

MIT lisansı altında dağıtılmaktadır.</br>
[![MIT License][license-shield]][license-url]


<!-- CONTACT -->
## İletişim

Emrah TOY - [@emrahtoy](https://instagram.com/emrahtoy) - emrah.toy@mtekbilisim.com

Proje linki : [https://github.com/mtekbilisim/goArenaPoc-backend](https://github.com/mtekbilisim/goArenaPoc-backend)



<!-- ACKNOWLEDGEMENTS -->
## Teşekkürler!
Bu bökümanın hazırlanmasında emeği geçen diğer kütüphaneler : 
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Img Shields](https://shields.io)
* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Pages](https://pages.github.com)
* [Animate.css](https://daneden.github.io/animate.css)
* [Loaders.css](https://connoratherton.com/loaders)
* [Slick Carousel](https://kenwheeler.github.io/slick)
* [Smooth Scroll](https://github.com/cferdinandi/smooth-scroll)
* [Sticky Kit](http://leafo.net/sticky-kit)
* [JVectorMap](http://jvectormap.com)
* [Font Awesome](https://fontawesome.com)





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://opensource.org/licenses/MIT
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/emrahtoy
[product-screenshot]: images/screenshot.png

-------------
<p align="center">
  <a href="http://www.mtekbilisim.com/">
    <img src="http://www.mtekbilisim.com/img/logo.png" alt="Logo">
  </a>
</p>
