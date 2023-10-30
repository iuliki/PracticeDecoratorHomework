import java.io.*;

public class FileDataSource implements DataSource {
    @Override
    public String read(String filePath) {
        // Implementați citirea din fișier și afișarea conținutului pe consolă
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Eroare la citirea fișierului: " + e.getMessage();
        }
        // Va trebui să tratați excepții aici

    }

    @Override
    public void write(String filePath, String content) {
        // Implementați scrierea conținutului în fișier pe disc
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
        // Va trebui să tratați excepții aici
    }
}
