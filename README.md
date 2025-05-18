# JWT Authentication Console Application

Ky projekt implementon një sistem bazik të autentikimit me JWT në Java.

## Struktura e Projektit (deri tash)

- **Main.java** – pika kryesore hyrëse e programit.
- **User.java** – klasa që përfaqëson një përdorues me `username` dhe `hashedPassword`.
- **UserDatabase.java** – një “bazë e të dhënave” në memorie që mban përdoruesit e paracaktuar.
- **HashUtil.java** – klasa për hashing të fjalëkalimeve duke përdorur algoritmin SHA-256.
- **TokenManager.java** – gjeneron JWT me username, kohë lëshimi dhe skadimi.
- **TokenManagerTest.java** –  teston gjenerimin, ndarjen dhe dekodimin e tokenit.
  
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

## Çfarë është JWT dhe për çfarë përdoret?

JWT (JSON Web Token) është një standard për sigurimin e informacionit në formë të koduar që përdoret kryesisht për autentikim dhe autorizim. Ai lejon shkëmbimin e sigurt të informacionit midis palëve, pa pasur nevojë të ruhet sesioni në server.

## Struktura e Token-it

JWT përbëhet nga tri pjesë:

1. Header – përmban informacion mbi algoritmin e nënshkrimit dhe llojin e token-it (p.sh. HS256 dhe JWT).
2. Payload – përmban deklaratat (claims), si identiteti i përdoruesit dhe kohët e krijimit dhe skadimit.
3. Signature – është nënshkrimi i token-it që siguron integritetin dhe autenticitetin e të dhënave.

## Informacioni në Payload dhe pse përdoret

Payload përmban informacion të rëndësishëm, si:

1. sub - identifikon përdoruesin (p.sh. emrin e përdoruesit), që është i rëndësishëm për autentikimin.
2. iat - koha kur token-i u krijua.
3. exp - koha kur token-i skadon, për të parandaluar përdorimin e tij pas periudhës së vlefshmërisë

Ky informacion ndihmon në verifikimin e vlefshmërisë dhe sigurisë së token-it.

## Biblioteka dhe mënyra e nënshkrimit

Për krijimin e token-it u përdor biblioteka java-jwt nga Auth0. Token-i nënshkruhet me algoritmin HMAC SHA-256 duke përdorur një çelës sekret (mysecret123), për të siguruar që token-i nuk është ndryshuar gjatë transmetimit dhe që buron nga një burim i besueshëm.

## Si funksionon verifikimi i token-it?

Në sistemin tonë, verifikimi i JWT token-it bëhet duke përdorur bibliotekën java-jwt. Procesi i verifikimit përfshin dy kontrollime kryesore:

Kontrolli i nënshkrimit (Signature Check):

1. Token-i verifikohet duke përdorur të njëjtin çelës sekret që është përdorur për ta nënshkruar atë.
2. Biblioteka kontrollon nëse nënshkrimi i token-it është i vlefshëm.
3. Nëse dikush tenton të ndryshojë token-in pa e ditur çelësin sekret, verifikimi do të dështojë.

Kontrolli i skadimit (Expiration Check):

1. Çdo token ka një kohë skadimi (5 minuta në rastin tonë).
2. Biblioteka kontrollon nëse data aktuale është para datës së skadimit të token-it.

## Veçoria e mbrojtur (Protected Feature)
Sistemi ynë ka një veçori të mbrojtur që mund të aksesohet vetëm me një token të vlefshëm:
Kur përdoruesi paraqet një token të vlefshëm, sistemi shfaq një mesazh sekret ("Secret data = 42").
Kjo është një simulim i një burimi të mbrojtur që mund të jetë një faqe e veçantë, API endpoint, ose ndonjë funksionalitet tjetër i kufizuar.

## Gabimet dhe trajtimi i tyre
Disa gabime që mund të ndodhin dhe si i trajtojmë:

1. Token i pavlefshëm:

Shkak: Nënshkrimi i token-it nuk përputhet me çelësin sekret.  
Trajtim: Shfaqim mesazhin "Access denied! Invalid or expired token."

2. Token i skaduar:

Shkak: Koha e token-it ka kaluar 5 minuta nga krijimi.  
Trajtim: Shfaqim të njëjtin mesazh si për token të pavlefshëm.

3. Token i dëmtuar:

Shkak: Token-i është ndryshuar manualisht ose formatuar gabimisht.  
Trajtim: Verifikimi dështon dhe shfaqet mesazhi i gabimit.

4. Token pa të dhëna të mjaftueshme:

Shkak: Token-i nuk përmban subjektin (username) ose të dhëna të tjera të nevojshme.  
Trajtim: Verifikimi konsiderohet i dështuar
