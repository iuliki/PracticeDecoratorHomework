import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionDecorator implements DataSource {
    private DataSource dataSource;
    private static final String encryptionKey = "MyAESKey12345678"; // O cheie de criptare validă pentru AES



    public EncryptionDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String read(String filePath) {
        String encryptedContent = dataSource.read(filePath);
        String decryptedContent = decrypt(encryptedContent, encryptionKey);
        return "Conținut decriptat: " + decryptedContent;
    }

    @Override
    public void write(String filePath, String content) {
        String encryptedContent = encrypt(content, encryptionKey);
        dataSource.write(filePath, encryptedContent);
    }

    private static String encrypt(String input, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "Eroare la criptare: " + e.getMessage();
        }
    }

    private static String decrypt(String input, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(input);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "Eroare la decriptare: " + e.getMessage();
        }
    }
}
