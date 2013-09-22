import junit.framework.*;

public class BowlingScoreTest extends TestCase {
    public final static int NA = BowlingScore.NA;
    public BowlingScoreTest(String name) {
        super(name);
    }

    /**
     * 空の初期状態を作る.
     * 10個のフレームがあり，すべて空
     */
    public void testCreate() {
        BowlingScore bs = new BowlingScore();

        int[] scores = bs.getScores();
        assertEquals("10個ある", 10, scores.length);
        for (int i = 0; i < BowlingScore.FRAME_COUNT; i++)
            assertEquals("最初は全部NA", BowlingScore.NA, scores[i]);

        assertEquals("最初のフレームは0番", 0, bs.getCurrentFrameNumber());
    }

    /**
     * 1投する．(2点)
     * 1番目のフレームは，NA．
     * 2投する．(3点)
     * 1番目のフレームは，5
     */
    public void testFirstFrame2and3() {
        BowlingScore bs = new BowlingScore();

        assertEquals("最初のフレームは0番", 0, bs.getCurrentFrameNumber());

        // 1投
        bs.pinDown(2);
        assertEquals("まだ未定", BowlingScore.NA, bs.getScore(0));

        // 2投
        bs.pinDown(3);
        assertEquals("5点", 5, bs.getScore(0));

        assertEquals("次フレームは1番", 1, bs.getCurrentFrameNumber());

        int[] scores = bs.getScores();
        int[] expected = { 5, NA, NA, NA, NA, NA, NA, NA, NA, NA };

        assertArrayEquals(expected,scores);
    }

    /**
     * 1投する．(2点)
     * 1番目のフレームは，NA．
     * 2投する．(8点)
     * 1番目のフレームは，未定
     */
    public void testFirstFrame2and8() {
        BowlingScore bs = new BowlingScore();

        // 1投
        bs.pinDown(2);

        // 2投
        bs.pinDown(8);

        int[] scores = bs.getScores();
        int[] expected = { NA, NA, NA, NA, NA, NA, NA, NA, NA, NA };

        assertArrayEquals(expected, scores);
    }

    public void test01() {
        BowlingScore bs = new BowlingScore();

        int[] pins = { 1, 2,  // 3
                       3, 4,  // 10
                       3, 4,  // 17
        };

        bs.pinDown(pins);

        int[] expected = { 3, 10, 17, NA, NA, NA, NA, NA, NA, NA };
        int[] scores = bs.getAccumulatedScores();

        assertArrayEquals(expected, scores);
    }

    public void test02() {
        BowlingScore bs = new BowlingScore();

        int[] pins = { 1, 9,  // 13
                       3, 4,  // 20
        };

        bs.pinDown(pins);

        int[] expected = { 13, 20, NA, NA, NA, NA, NA, NA, NA, NA };
        int[] scores = bs.getAccumulatedScores();

        assertArrayEquals(expected, scores);
    }

    public void supportTestPinDowns(int[] pins, int[] expected) {
        BowlingScore bs = new BowlingScore();
        bs.pinDown(pins);
        int [] result = bs.getAccumulatedScores();
        assertArrayEquals(expected, result);
    }

    public void test03() {
        int[] pins = { 1, 9,  // 13
                       3, 7,  // NA
        };
        int[] expected = { 13, NA, NA, NA, NA, NA, NA, NA, NA, NA };
        supportTestPinDowns(pins, expected);
    }

    public void test04() {
        int[] pins = { 1, 9, // 13
                       3, 7, // 27
                       4, 5  // 
        };
        int[] expected = { 13, 27, 36, NA, NA, NA, NA, NA, NA, NA };
        supportTestPinDowns(pins, expected);
    }

    public void test05() {
        int[] pins = { 10,   // 16
                       3, 3, // 22
        };
        int[] expected = { 16, 22, NA, NA, NA, NA, NA, NA, NA, NA };
        supportTestPinDowns(pins, expected);
    }

    public void test06() {
        int[] pins = { 10,   // 20
                       3, 7, // 34
                       4, 5, // 43
        };
        int[] expected = { 20, 34, 43, NA, NA, NA, NA, NA, NA, NA };
        supportTestPinDowns(pins, expected);
    }

    public void test07() {
        int[] pins = { 10,   // 30
                       10,   // 54
                       10,   // 73
                       4, 5, // 82
        };
        int[] expected = { 30, 54, 73, 82, NA, NA, NA, NA, NA, NA };
        supportTestPinDowns(pins, expected);
    }

    public void test08() {
        int[] pins = { 0, 0,   // 2
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0
        };

        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        supportTestPinDowns(pins, expected);
    }

    public void test09() {
        int[] pins = { 0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       10,
                       10,
                       10,10,10
        };

        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 30, 60, 90 };
        supportTestPinDowns(pins, expected);
    }

    public void test10() {
        int[] pins = { 0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       10,
                       10,
                       9,1,10
        };

        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 29, 49, 69 };
        supportTestPinDowns(pins, expected);
    }
    
    public void test11() {
        int[] pins = { 0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       10,
                       10,
                       9,1,1
        };

        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 29, 49, 60 };
        supportTestPinDowns(pins, expected);
    }
    
    public void test12() {
        int[] pins = { 0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       0, 0,
                       10,
                       10,
                       10,1,1
        };

        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 30, 51, 63 };
        supportTestPinDowns(pins, expected);
    }

    
    public void assertArrayEquals(int [] expected, int[] result) {
        if (expected.length != result.length)
            assert(false);
        for (int i = 0; i < expected.length; i++)
            assertEquals(String.valueOf(i),expected[i], result[i]);
    }

}
