
public class SimHash {

    //清洗文本：保留中文、字母、数字，过滤符号空格
    public static String cleanText(String src) {
        StringBuilder sb = new StringBuilder();
        for (char c : src.toCharArray()) {
            if ((c >= 0x4E00 && c <= 0x9FFF) || Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static long getHash(String token) {
        long hash = 0;
        for (char ch : token.toCharArray()) {
            hash = (hash << 5) + hash + ch;
            hash ^= hash >> 17;
        }
        return hash;
    }

    // 2‑gram滑动窗口：连续两个字符作为一个token
    public static long computeSimHash(String text) {
        double[] weight = new double[64];
        int len = text.length();
        if(len < 2){
            //不足2字符，退化成单字符
            for(int i=0;i<len;i++){
                String token = String.valueOf(text.charAt(i));
                long h = getHash(token);
                for(int bit=0;bit<64;bit++){
                    long bitVal = (h >>> bit) & 1L;
                    if(bitVal ==1) weight[bit] +=1.0;
                    else weight[bit] -=1.0;
                }
            }
        }else {
            for (int i = 0; i <= len - 2; i++) {
                //取i,i+1两个字符
                String token = text.substring(i, i + 2);
                long h = getHash(token);
                for (int bit = 0; bit < 64; bit++) {
                    long bitVal = (h >>> bit) & 1L;
                    if (bitVal == 1) {
                        weight[bit] += 1.0;
                    } else {
                        weight[bit] -= 1.0;
                    }
                }
            }
        }
        long simhash = 0L;
        for(int bit=0;bit<64;bit++){
            if(weight[bit]>0){
                simhash |= (1L << bit);
            }
        }
        return simhash;
    }

    //海明距离
    public static int hammingDistance(long h1, long h2) {
        return Long.bitCount(h1 ^ h2);
    }

    //相似度0~1
    public static double calcSimilarity(long h1, long h2) {
        int dist = hammingDistance(h1, h2);
        double sim = (64.0 - dist) / 64.0;
        return Math.max(sim,0.0);
    }
}

