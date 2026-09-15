
import java.io.*;

public class FileUtil {
    // 读取文件全部文本
    public static String readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

    // 写入结果，保留两位小数
    public static boolean writeFile(String filePath, double rate) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.printf("%.2f", rate);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
