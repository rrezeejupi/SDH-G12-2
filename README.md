
# Autentifikimi me JWT në Java

Ky projekt implementon një sistem autentifikimi klient-server duke përdorur JSON Web Tokens (JWT) për menaxhimin e sesioneve dhe sigurimin e të dhënave.

## Përshkrimi i Projektit

Ky aplikacion përbëhet nga një server dhe një klient që komunikojnë nëpërmjet sockets. Serveri pranon kredencialet e përdoruesit, i verifikon ato, dhe në rast suksesi gjeneron një JWT të nënshkruar me çelës RSA. Klienti përdor tokenin për të kërkuar të dhëna të mbrojtura nga serveri.

## Mjedisi i Zhvillimit

Ky projekt është zhvilluar dhe testuar në mjedisin e mëposhtëm:

- Java SE Development Kit (JDK) 8 ose më i lartë
- Operative System: Windows 10 / 11
- Libraritë e përdorura (JAR files):
  - [java-jwt-4.4.0.jar](https://github.com/auth0/java-jwt) - për menaxhimin e JSON Web Tokens (JWT)
  - [jackson-core-2.15.2.jar](https://github.com/FasterXML/jackson-core), [jackson-databind-2.15.2.jar](https://github.com/FasterXML/jackson-databind), [jackson-annotations-2.15.2.jar](https://github.com/FasterXML/jackson-annotations) - për serializimin dhe deserializimin e JSON


## Udhëzime për ekzekutimin e projektit

### 1. Përgatitja e Mjedisit

Sigurohuni që keni vendosur të gjitha `.jar` librarite në të njëjtin folder ku ndodhen edhe files `.java` dhe `.class` të projektit.

### 2. Kompilimi

Në terminalin tuaj, në folderin ku ndodhen files të projektit, ekzekutoni komandën:

```bash
javac -cp ".;java-jwt-4.4.0.jar;jackson-core-2.15.2.jar;jackson-databind-2.15.2.jar;jackson-annotations-2.15.2.jar" *.java
```

### 3. Nisja e Serverit

Pasi të jetë kompiluar, nisni serverin me komandën:

```bash
java -cp ".;java-jwt-4.4.0.jar;jackson-core-2.15.2.jar;jackson-databind-2.15.2.jar;jackson-annotations-2.15.2.jar" Server
```

Serveri do të presë për lidhje nga klienti.

### 4. Nisja e Klientit

Në një dritare tjetër terminali, nisni klientin me komandën:

```bash
java -cp ".;java-jwt-4.4.0.jar;jackson-core-2.15.2.jar;jackson-databind-2.15.2.jar;jackson-annotations-2.15.2.jar" Client
```
### 5. Përdorimi

- Klienti do t'ju kërkojë të shkruani `username` dhe `password`.
- Shembull kredencialesh të pranuara nga serveri:
  - Username: `alice`
  - Password: `password123`
- Pas autentifikimit të suksesshëm, klienti do të marrë një token JWT.
- Më pas, klienti mund të dërgojë komandat:
  - `request_data` për të kërkuar të dhëna të mbrojtura nga serveri.
  - `logout` për të dalë nga sesioni.

## Strukturë Projekti

```
src/
 └─ main/
     └─ java/
         ├─ Client.java
         ├─ Server.java
         ├─ TokenManager.java
         ├─ User.java
         ├─ UserDatabase.java
         ├─ HashUtil.java
         ├─ RSAKeyUtil.java
         ├─ TokenManagerTest.java
         ├─ java-jwt-4.4.0.jar
         ├─ jackson-core-2.15.2.jar
         ├─ jackson-databind-2.15.2.jar
         ├─ jackson-annotations-2.15.2.jar
```

## Përshkrim i Modulave Kryesore

- **Server.java** - Serveri i aplikacionit që pranon lidhje nga klienti, verifikon kredencialet, dhe gjeneron token JWT.
- **Client.java** - Klienti që komunikon me serverin, dërgon kredencialet dhe kërkon të dhëna të mbrojtura.
- **TokenManager.java** - Menaxhon krijimin dhe verifikimin e JSON Web Tokens.
- **UserDatabase.java** - Ruajtja dhe menaxhimi i përdoruesve dhe kredencialeve të tyre.
- **HashUtil.java** - Funksione për hashim të fjalëkalimeve.
- **RSAKeyUtil.java** - Gjenerimi dhe menaxhimi i çelësave RSA për nënshkrimin e JWT-ve.

## Problemet e Njohura

- Sigurohuni që libraritë `jackson-core`, `jackson-databind` dhe `jackson-annotations` janë të gjitha të përfshira në classpath për të shmangur gabimet e mungesës së klasave.
- Mos harroni të nisni serverin para klientit.
- Kujdes me versionet e librarive; versionet që përdoren këtu janë ato me të cilat projekti është testuar.

## Pamje nga Terminali

Më poshtë janë shembuj të ekzekutimit të programit në terminal, për serverin dhe klientin.

### Serveri
![image](https://github.com/user-attachments/assets/6ddfeda8-b729-44a5-a0d7-149f7c8b0765)

### Klienti
![image](https://github.com/user-attachments/assets/089e5a66-5e2f-4f73-8b94-18d39cf09e6e)
