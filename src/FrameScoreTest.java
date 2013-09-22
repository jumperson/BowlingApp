import static org.junit.Assert.*;
import org.junit.*;

public class FrameScoreTest{
	/**
     * FrameScoreを作る。
     * ストライクでないことを確認する。
     */
	@Test
	public void testIsNotStrike(){
		int firstScore = 0;
		FrameScore fs = new FrameScore(firstScore);
		assertFalse("ストライクでない。",fs.isStrike());
	}
	
	/**
     * ストライクであることを確認する。
     */
	@Test
	public void testIsStrike(){
		int firstScore = 10;
		FrameScore fs = new FrameScore(firstScore);
		assertTrue("ストライクである。",fs.isStrike());
	}
	
	/**
     * フレームが終えていることを確認する。
     */
	@Test
	public void testFinished1(){
		int firstScore = 10;
		FrameScore fs = new FrameScore(firstScore);
		assertTrue("終えている。",fs.finished());
	}
	
	/**
     * フレームが終えていることを確認する。
     */
	@Test
	public void testFinished2(){
		int firstScore = 1;
		int secondScore = 9;
		FrameScore fs = new FrameScore(firstScore);
		fs.setSecondScore(secondScore);
		assertTrue("終えている。",fs.finished());
	}

	/**
     * フレームが終えていないことを確認する。
     */
	@Test
	public void testNotFinished(){
		int firstScore = 1;
		FrameScore fs = new FrameScore(firstScore);
		assertFalse("終えていない。",fs.finished());
	}
	
	/**
     * スコアが確定していないことを確認する。
     */
	@Test
	public void testNotFixedScore1(){
		int firstScore = 10;
		FrameScore fs = new FrameScore(firstScore);
		assertTrue("確定していない。",fs.notFixedScore());
	}

	/**
     * スコアが確定していないことを確認する。
     */
	@Test
	public void testNotFixedScore2(){
		int firstScore = 1;
		int secondScore = 9;
		FrameScore fs = new FrameScore(firstScore);
		fs.setSecondScore(secondScore);
		assertTrue("確定していない。",fs.notFixedScore());
	}

	/**
     * スコアが確定していないことを確認する。
     */
	@Test
	public void testNotFixedScore3(){
		int firstScore = 1;
		FrameScore fs = new FrameScore(firstScore);
		assertTrue("確定していない。",fs.notFixedScore());
	}
	
	/**
     * スコアが確定していることを確認する。
     */
	@Test
	public void testFixedScore(){
		int firstScore = 1;
		int secondScore = 1;
		FrameScore fs = new FrameScore(firstScore);
		fs.setSecondScore(secondScore);
		assertFalse("確定している。",fs.notFixedScore());
	}
	
	/**
     * スペアであることを確認する。
     */
	@Test
	public void testIsSpare(){
		int firstScore = 1;
		int secondScore = 9;
		FrameScore fs = new FrameScore(firstScore);
		fs.setSecondScore(secondScore);
		assertTrue("スペアである。",fs.isSpare());
	}
	
	/**
     * スペアでないことを確認する。
     */
	@Test
	public void testIsNotSpare1(){
		int firstScore = 1;
		int secondScore = 8;
		FrameScore fs = new FrameScore(firstScore);
		fs.setSecondScore(secondScore);
		assertFalse("スペアでない。",fs.isSpare());
	}
	
	/**
     * スペアでないことを確認する。
     */
	@Test
	public void testIsNotSpare2(){
		int firstScore = 10;
		FrameScore fs = new FrameScore(firstScore);
		assertFalse("スペアでない。",fs.isSpare());
	}
	
	/**
     * スペアでないことを確認する。
     */
	@Test
	public void testIsNotSpare3(){
		int firstScore = 1;
		FrameScore fs = new FrameScore(firstScore);
		assertFalse("スペアでない。",fs.isSpare());
	}
	
	/**
     * 1投目の値が取得できること。
     */
	@Test
	public void testGetFirstScore1(){
		int firstScore = 1;
		FrameScore fs = new FrameScore(firstScore);
		assertEquals(firstScore,fs.getFirstScore());
	}
	
	/**
     * 1投目の値が取得できること。（ストライクの場合）
     */
	@Test
	public void testGetFirstScore2(){
		int firstScore = 10;
		FrameScore fs = new FrameScore(firstScore);
		assertEquals(firstScore,fs.getFirstScore());
	}
	
	/**
     * 1投目と2投目の合計が取得できること。
     */
	@Test
	public void testGetFirstAndSecondScore1(){
		int firstScore = 1;
		int secondScore = 1;
		FrameScore fs = new FrameScore(firstScore);
		fs.setSecondScore(secondScore);
		assertEquals(firstScore + secondScore,fs.getFirstAndSecondScore());
	}

	/**
     * 1投目と2投目の合計が取得できること。（スペアの場合）
     */
	@Test
	public void testGetFirstAndSecondScore2(){
		int firstScore = 1;
		int secondScore = 9;
		FrameScore fs = new FrameScore(firstScore);
		fs.setSecondScore(secondScore);
		assertEquals(firstScore + secondScore,fs.getFirstAndSecondScore());
	}
	

	/**
     * 範囲外の値を設定し、例外が発生すること。
     */
	@Test(expected= IllegalArgumentException.class)
	public void testNotInRange1() { 
		FrameScore fs = new FrameScore(-1);
	}

	/**
     * 範囲外の値を設定し、例外が発生すること。
     */
	@Test(expected= IllegalArgumentException.class)
	public void testNotInRange2() { 
		FrameScore fs = new FrameScore(11);
	}
	
	/**
     * 範囲外の値を設定し、例外が発生すること。
     */
	@Test(expected= IllegalArgumentException.class)
	public void testNotInRange3() { 
		FrameScore fs = new FrameScore(1);
		fs.setSecondScore(-1);
	}
	
	/**
     * 範囲外の値を設定し、例外が発生すること。
     */
	@Test(expected= IllegalArgumentException.class)
	public void testNotInRange4() { 
		FrameScore fs = new FrameScore(5);
		fs.setSecondScore(6);
	}
	
	/**
     * 範囲外の値を設定し、例外が発生すること。
     */
	@Test(expected= NullPointerException.class)
	public void testGetNullScore() { 
		FrameScore fs = new FrameScore(5);
		fs.getFirstAndSecondScore();
	}

}
