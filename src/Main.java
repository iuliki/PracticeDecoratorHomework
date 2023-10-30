public class Main {
    public static void main(String[] args) {
        // Creați o sursă de date pentru un fișier
        DataSource fileDataSource = new FileDataSource();

        String filePath = "D:\\fac emanuel it\\Anul 3\\Sem 1\\Ingineria Software\\PracticeDecoratorPatternHomework\\text.txt";

// Citire din fișier
        String fileContent = fileDataSource.read(filePath);

        // Scriere într-un fișier
        String contentToWrite = "Ma numesc Bulugu Iulia, am 22 de ani si sunt Studenta la Universitatea Emanuel. Am inteles in procent de 80% \n" +
                " ce am facut aici! Nu prea am notiuni de comprimare si \n" +
                " decomprimare dar am aplicat functiile pe care le-am gasit";
        fileDataSource.write("text.txt", contentToWrite);
        System.out.println("Conținutul a fost scris în fișier.");

        // Adăugați decoratoare pentru criptare și comprimare
        DataSource encryptedFileDataSource = new EncryptionDecorator(fileDataSource);
        DataSource compressedEncryptedFileDataSource = new CompressionDecorator(encryptedFileDataSource);

        // Citire dintr-un fișier criptat și comprimat
        String encryptedCompressedContent = compressedEncryptedFileDataSource.read("output_encrypted_compressed.txt");
        System.out.println("Conținutul citit din fișier criptat și comprimat: " + encryptedCompressedContent);

        // Scriere într-un fișier criptat și comprimat
        String contentToEncryptCompress = "Ma numesc Bulugu Iulia, am 22 de ani si sunt Studenta la Universitatea Emanuel. Am inteles in procent de 80% \n ce am facut aici! Nu prea am notiuni de comprimare si \n decomprimare dar am aplicat functiile pe care le-am gasit";
        compressedEncryptedFileDataSource.write("output_encrypted_compressed.txt", contentToEncryptCompress);
        System.out.println("Conținutul a fost scris în fișier criptat și comprimat.");

        System.out.println(contentToEncryptCompress);
        System.out.println(compressedEncryptedFileDataSource);


    }
}
