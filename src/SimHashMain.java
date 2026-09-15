
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimHashMain {

    // 读取文本文件，返回文件全部内容
    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // ========== 修改这里的文件路径 ==========
        String pathOrig = "orig.txt";    //原文
        String pathCopy = "orig_0.8_add.txt";   //待查重文本

        //读取文件
        String textOrig = readFile(pathOrig);
        String textCopy = readFile(pathCopy);

        //计算simhash
        long hashOrig = SimHash.computeSimHash(SimHash.cleanText(textOrig));
        long hashCopy = SimHash.computeSimHash(SimHash.cleanText(textCopy));

        //计算相似度
        double similarity = SimHash.calcSimilarity(hashOrig, hashCopy);

        System.out.println("原文SimHash指纹：" + hashOrig);
        System.out.println("待检测文本SimHash指纹：" + hashCopy);
        System.out.printf("文本相似度：%.4f\n", similarity);

        //查重阈值，一般0.6作为参考，可自行修改
        if(similarity > 0.6){
            System.out.println("判定：疑似抄袭");
        }else{
            System.out.println("判定：相似度较低");
        }
    }
}

