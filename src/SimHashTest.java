
import org.junit.Test;
import static org.junit.Assert.*;

public class SimHashTest {
    @Test
    public void test1SameText(){
        String t1 = "今天是星期天，天气晴，今天晚上我要去看电影。";
        String t2 = "今天是星期天，天气晴，今天晚上我要去看电影。";
        long h1 = SimHash.computeSimHash(SimHash.cleanText(t1));
        long h2 = SimHash.computeSimHash(SimHash.cleanText(t2));
        double s = SimHash.calcSimilarity(h1,h2);
        assertTrue(s>0.99);
    }
    @Test
    public void test2Sample(){
        String t1 = "今天是星期天，天气晴，今天晚上我要去看电影。";
        String t2 = "今天是周天，天气晴朗，我晚上要去看电影。";
        long h1 = SimHash.computeSimHash(SimHash.cleanText(t1));
        long h2 = SimHash.computeSimHash(SimHash.cleanText(t2));
        double s = SimHash.calcSimilarity(h1,h2);
        assertTrue(s>0.6);
    }
    @Test
    public void test3NoRelated(){
        String t1 = "人工智能";
        String t2 = "篮球足球";
        long h1 = SimHash.computeSimHash(SimHash.cleanText(t1));
        long h2 = SimHash.computeSimHash(SimHash.cleanText(t2));
        assertFalse(h1 == h2);
       
    }
    @Test
    public void test4BothEmpty(){
        String t1 = "";
        String t2 = "";
        long h1 = SimHash.computeSimHash(SimHash.cleanText(t1));
        long h2 = SimHash.computeSimHash(SimHash.cleanText(t2));
        double s = SimHash.calcSimilarity(h1,h2);
        assertTrue(s>0.99);
    }
    @Test
    public void test5OneEmpty(){
        String t1 = "";
        String t2 = "计算机科学";
        long h1 = SimHash.computeSimHash(SimHash.cleanText(t1));
        long h2 = SimHash.computeSimHash(SimHash.cleanText(t2));
        
       
        assertFalse(h1 == h2);
      
    }
    @Test
    public void test6FilterPunct(){
        String t1 = "！@#￥%……&*（）今天，测试。";
        String res = SimHash.cleanText(t1);
        assertEquals("今天测试", res);
    }
    @Test
    public void test7EnNum(){
        String t1 = "abc123";
        String t2 = "abc123";
        long h1 = SimHash.computeSimHash(SimHash.cleanText(t1));
        long h2 = SimHash.computeSimHash(SimHash.cleanText(t2));
        double s = SimHash.calcSimilarity(h1,h2);
        assertTrue(s>0.99);
    }
    @Test
    public void test8CutWords(){
        String t1 = "Java论文查重课程作业";
        String t2 = "Java论文查重作业";
        long h1 = SimHash.computeSimHash(SimHash.cleanText(t1));
        long h2 = SimHash.computeSimHash(SimHash.cleanText(t2));
        double s = SimHash.calcSimilarity(h1,h2);
        assertTrue(s>0.7);
    }
    @Test
    public void test9Hamming0(){
        long h1 = 0x1122334455667788L;
        long h2 = 0x1122334455667788L;
        assertEquals(0,SimHash.hammingDistance(h1,h2));
    }
    @Test
    public void test10Hamming1(){
        long h1 = 0L;
        long h2 = 1L;
        assertEquals(1,SimHash.hammingDistance(h1,h2));
    }
    @Test
    public void test11AddWords(){
        String t1 = "程序设计";
        String t2 = "程序设计与数据结构";
        long h1 = SimHash.computeSimHash(SimHash.cleanText(t1));
        long h2 = SimHash.computeSimHash(SimHash.cleanText(t2));
        double s = SimHash.calcSimilarity(h1,h2);
        assertTrue(s>0.5);
    }
}
