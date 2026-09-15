
public class PaperCheckMain {
    public static void main(String[] args) {
        // 参数：原文路径 抄袭文件路径 输出文件路径
        if (args.length != 3) {
            System.err.println("参数错误！用法：java -jar main.jar 原文路径 抄袭路径 输出路径");
            return;
        }
        String origPath = args[0];
        String copyPath = args[1];
        String outPath = args[2];

        String origText = FileUtil.readFile(origPath);
        String copyText = FileUtil.readFile(copyPath);
        if (origText == null || copyText == null) {
            System.err.println("读取文件失败！");
            return;
        }

        String cleanOrig = SimHash.cleanText(origText);
        String cleanCopy = SimHash.cleanText(copyText);

        long hashOrig = SimHash.computeSimHash(cleanOrig);
        long hashCopy = SimHash.computeSimHash(cleanCopy);
        double repeatRate = SimHash.calcSimilarity(hashOrig, hashCopy);
        //新增打印，控制台输出相似度
        System.out.println("文本相似度 = " + String.format("%.2f",repeatRate));
        boolean ok = FileUtil.writeFile(outPath, repeatRate);
        if (!ok) {
            System.err.println("写入结果文件失败！");
        }
    }
}

