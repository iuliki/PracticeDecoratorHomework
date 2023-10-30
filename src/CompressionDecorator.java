import java.io.*;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionDecorator implements DataSource {
    private DataSource dataSource;

    public CompressionDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String read(String filePath) {
        try (InputStream fileInput = new FileInputStream(filePath);
             InputStream gzipInput = new GZIPInputStream(fileInput);
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInput))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return "Conținut decomprimat: " + content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Eroare la decomprimare: " + e.getMessage();
        }
    }

    @Override
    public void write(String filePath, String content) {
        try (OutputStream fileOutput = new FileOutputStream(filePath);
             OutputStream gzipOutput = new GZIPOutputStream(fileOutput);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(gzipOutput))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Eroare la comprimare: " + e.getMessage());
        }
    }
}
