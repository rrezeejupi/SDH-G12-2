#JWT Authentication Console Application

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

## Si funksionon hashing-u me SHA-256 në Java?

Përdorim klasën `MessageDigest` të Java-s për të aplikuar algoritmin SHA-256 në fjalëkalimin e dhënë. Procesi është:

1. Fjalëkalimi konvertohet në bytes.
2. Përdoret SHA-256 për të krijuar një hash byte array.
3. Ky byte array konvertohet në një string hexadecimale për ruajtje dhe krahasim.

