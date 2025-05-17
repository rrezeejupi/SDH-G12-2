# JWT Authentication Console Application

Ky projekt implementon një sistem bazik të autentikimit me JWT në Java.

## Struktura e Projektit (deri tash)

- **Main.java** – pika kryesore hyrëse e programit.
- **User.java** – klasa që përfaqëson një përdorues me `username` dhe `hashedPassword`.
- **UserDatabase.java** – një “bazë e të dhënave” në memorie që mban përdoruesit e paracaktuar.
- **HashUtil.java** – klasa për hashing të fjalëkalimeve duke përdorur algoritmin SHA-256.

## Pse përdorim hashing për fjalëkalimet?

Fjalëkalimet nuk ruhen kurrë në formë të thjeshtë (plaintext) për arsye sigurie. Nëse dikush merr akses në të dhënat, fjalëkalimet e hash-uara janë të mbrojtura dhe nuk mund të lexohet fjalëkalimi origjinal. Hashing është një proces njëkahësh që e bën të pamundur kthimin e vlerës së hash-it në fjalëkalimin origjinal.

## Simulimi i Bazës së të Dhënave

Në këtë projekt, përdoruesit ruhen në memorie duke përdorur një `Map` në klasën `UserDatabase`. Kjo është një simulim dhe nuk përdor një bazë të dhënash reale.

## Si funksionon hashing me SHA-256 në Java?

Përdorim klasën `MessageDigest` të Java-s për të aplikuar algoritmin SHA-256 në fjalëkalimin e dhënë. Procesi është:

1. Fjalëkalimi konvertohet në bytes.
2. Përdoret SHA-256 për të krijuar një hash byte array.
3. Ky byte array konvertohet në një string hexadecimale për ruajtje dhe krahasim.

## Rrjedha e autentikimit (login)
1.	Përdoruesi jep username dhe password.
2.	Sistemi kërkon në databazën simulues në memorie nëse ekziston përdoruesi me atë emër.
3.	Nëse përdoruesi nuk ekziston, shfaqet mesazh gabimi.
4.	Nëse ekziston, bëhet hash i fjalëkalimit të futur dhe krahasohet me hash-in e ruajtur.
5.	Nëse përputhen, përdoruesi autentikohet dhe sistemi vazhdon me gjenerimin e JWT-së.
6.	Nëse nuk përputhen, shfaqet mesazh gabimi për fjalëkalim të pasaktë.

## Si bëhet verifikimi i fjalëkalimit?

Fjalëkalimi që përdoruesi jep si input nuk krahasohet direkt me fjalëkalimin e ruajtur. Në vend të kësaj, përdoret një funksion hash-i për ta transformuar fjalëkalimin në një vlerë të koduar (hashed) duke përdorur algoritmin SHA-256. Ky hash krahasohet më pas me hash-in e ruajtur për përdoruesin në databazë.

## Si funksionon logjika e suksesit ose dështimit gjatë login-it?

Nëse përdoruesi ekziston në databazë dhe hash-i i fjalëkalimit të dhënë përputhet me atë që është ruajtur:

1. Konsola shfaq mesazh suksesi.
2. Thirret funksioni për të gjeneruar një JWT token që do të përdoret për qasje të mbrojtur më vonë.

Nëse përdoruesi nuk ekziston ose fjalëkalimi është i gabuar:

1. Konsola shfaq një mesazh gabimi për përdoruesin ose për fjalëkalimin e pasaktë.
2. Nuk lejohet vazhdimi pa kredenciale të sakta.
