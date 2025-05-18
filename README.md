
# Projekti: Autentifikimi me JWT në Java

Ky projekt implementon një sistem autentifikimi klient-server duke përdorur JSON Web Tokens (JWT) për menaxhimin e sesioneve dhe sigurimin e të dhënave.

## Përshkrimi i Projektit

Ky aplikacion përbëhet nga një server dhe një klient që komunikojnë nëpërmjet sockets. Serveri pranon kredencialet e përdoruesit, i verifikon ato, dhe në rast suksesi gjeneron një JWT të nënshkruar me çelës RSA. Klienti përdor tokenin për të kërkuar të dhëna të mbrojtura nga serveri.

## Mjedisi i Zhvillimit

Ky projekt është zhvilluar dhe testuar në mjedisin e mëposhtëm:

- Java SE Development Kit (JDK) 8 ose më i lartë
- Operative System: Windows 10 / 11
- Libraritë e përdorura (JAR files):
  - `java-jwt-4.4.0.jar` - për menaxhimin e JSON Web Tokens (JWT)
  - `jackson-core-2.15.2.jar`, `jackson-databind-2.15.2.jar`, `jackson-annotations-2.15.2.jar` - për serializimin dhe deserializimin e JSON

## Udhëzime për ekzekutimin e projektit

### 1. Përgatitja e Mjedisit

Sigurohuni që keni vendosur të gjitha `.jar` bibliotekat në të njëjtin folder ku ndodhen edhe skedarët `.java` dhe `.class` të projektit.

### 2. Kompilimi

Në terminalin tuaj, në folderin ku ndodhen skedarët e projektit, ekzekutoni komandën:

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
