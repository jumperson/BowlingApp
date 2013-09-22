public class FrameScore{
	
	private int _firstScore ;
	private int _secondScore ;
	private static final int _na = -100;
	private static final int _minScore = 0;
	private static final int _maxScore = 10;
	
	public FrameScore(int firstScore){
		this._checkInRange(firstScore);
		this._firstScore = firstScore;
		this._secondScore = _na;
	}
	
	private void _checkInRange(int score) {
		if (score < _minScore || score > _maxScore)
			throw new IllegalArgumentException("範囲外の値です。");
	}

	public boolean isStrike() {
		return this._firstScore == 10;
	}
	
	public boolean finished(){
		if(isStrike())
			return true;
		return !this._isNullScore(this._secondScore);
	}
	
	public boolean notFixedScore(){
		if(!this.finished())
			return true;
		if(this.isStrike())
			return true;
		if(this.isSpare())
			return true;
		return false;
	}
	
	public void setSecondScore(int score){
		this._checkInRange(score);
		this._checkInRange(this._firstScore + score);
		this._secondScore = score;
	}

	public boolean isSpare(){
		if(!this.finished())
			return false;
		if(this.isStrike())
			return false;
		return this.getFirstAndSecondScore() == 10;
	}
	
	public int getFirstScore(){
		return this._firstScore;
	}
	
	public int getFirstAndSecondScore(){
		this._checkNullScore(this._secondScore);		
		return this._firstScore + this._secondScore;
	}
	
	private void _checkNullScore(int score) {
		if(this._isNullScore(score))
			throw new NullPointerException("値が設定されていません。");
	}
	
	private boolean _isNullScore(int score){
		return score == _na;
	}
}
