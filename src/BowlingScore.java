import java.util.ArrayList;

public class BowlingScore {

	public final static int NA = -100;
	public final static int FRAME_COUNT = 10;

	private ArrayList<FrameScore> _frameScores;
	
	public BowlingScore(){
		this._frameScoresInit();
	}
		
	private void _frameScoresInit(){
		_frameScores = new ArrayList<FrameScore>();
	}

	public int[] getScores() {
		return _CreateScores();
	}

	public int getCurrentFrameNumber() {
		int size = this._frameScores.size();
		if(size == 0)
			return size;
		if(this._frameScores.get(size-1).finished())
			return size;
		return size -1;
	}

	public void pinDown(int pinNum) {
		int size = this._frameScores.size();
		if(size == 0){
			this._frameScores.add(new FrameScore(pinNum));
			return;
		}
		FrameScore currentFS = this._frameScores.get(size-1);
		if(currentFS.finished()){
			this._frameScores.add(new FrameScore(pinNum));
			return;
		}
		currentFS.setSecondScore(pinNum);
	}
	
	public void pinDown(int[] pins) {
		for(int i = 0; i < pins.length; i++){
			if(pins[i] == NA)
				return;
			pinDown(pins[i]);
		}
	}
	
	private int[] _CreateScores()
	{
		int[] scores = new int[]{NA,NA,NA,NA,NA,NA,NA,NA,NA,NA};
		if(this._frameScores.size() == 0){
			return scores;
		}
		
		for(int i = 0; i < this._frameScores.size(); i++)
		{
			if(this._isFirstFrame(i))
				this._setCurrentScore(i,scores);
			if(this._isSecondFrame(i)){
				this._setOneBeforeScore(i, scores);
				this._setCurrentScore(i, scores);
			}
			if(this._isAnotherFrame(i)){
				this._setTwoBeforeScore(i,scores);
				this._setOneBeforeScore(i, scores);
				this._setCurrentScore(i, scores);
			}
		}
		return scores;
	}
	
	private void _setTwoBeforeScore(int i, int[] scores) {
		FrameScore currentFS = this._frameScores.get(i);
		FrameScore oneBeforeFS = this._frameScores.get(i-1);
		FrameScore twoBeforeFS = this._frameScores.get(i-2);
		if(twoBeforeFS.isStrike() && oneBeforeFS.isStrike()){
			if(i < 3){
				scores[i-2] = twoBeforeFS.getFirstScore() + oneBeforeFS.getFirstScore() + currentFS.getFirstScore();
			}
			else{
				scores[i-2] = scores[i-3] + twoBeforeFS.getFirstScore() + oneBeforeFS.getFirstScore() + currentFS.getFirstScore();
			}
		}
	}

	private void _setCurrentScore(int index, int[] scores) {
		if(index > scores.length-1)
			return;
		FrameScore currentFS = this._frameScores.get(index);
		if(currentFS.notFixedScore())
			return ;
		if(index == 0)
			scores[index] = currentFS.getFirstAndSecondScore();
		else
			scores[index] = currentFS.getFirstAndSecondScore() + scores[index - 1];
	}
	
	private void _setOneBeforeScore(int index, int[] scores){
		FrameScore oneBeforeFS = this._frameScores.get(index-1);
		FrameScore currentFS = this._frameScores.get(index);
		if(oneBeforeFS.isStrike()){
			if(currentFS.isStrike())
				return;
			if(this._isAnotherFrame(index))
				scores[index-1] = scores[index-2] + oneBeforeFS.getFirstScore() + currentFS.getFirstAndSecondScore();
			else
				scores[index-1] = oneBeforeFS.getFirstScore() + currentFS.getFirstAndSecondScore();
		}
		if(oneBeforeFS.isSpare()){
			if(index < 2)
				scores[index-1] = oneBeforeFS.getFirstAndSecondScore() + currentFS.getFirstScore();
			else
				scores[index-1] = scores[index-2] + oneBeforeFS.getFirstAndSecondScore() + currentFS.getFirstScore();
		}		
	}

	private boolean _isFirstFrame(int frameNum) {
		return frameNum == 0;
	}
	
	private boolean _isSecondFrame(int frameNum){
		return frameNum == 1;
	}
	
	private boolean _isAnotherFrame(int frameNum){
		if(this._isFirstFrame(frameNum))
			return false;
		if(this._isSecondFrame(frameNum))
			return false;
		return true;
	}
	
	

	public int getScore(int frameNum) {
		return _CreateScores()[frameNum];	
	}
	
	public int[] getAccumulatedScores() {
		return _CreateScores();
	}
}
