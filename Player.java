
public class Player extends Unit{
	Direction direction;
	int actionPoints;
	boolean isOverWatch;	
	
	enum Direction{
		UP, DOWN, LEFT, RIGHT;
	}
	
	public Player(Point pos) {
		super('^', true, 5, pos, true);
		direction = Direction.UP;
		actionPoints = 3;
		isOverWatch = false;
	}
	
	public void setDirection(Direction d){
		direction = d;
		if ( d.ordinal() == 0){
			displayChar = '^';
		}else if (d.ordinal() == 1){
			displayChar = 'v';
		}else if (d.ordinal() == 2){
			displayChar = '<';
		}else if (d.ordinal() == 3){
			displayChar = '>';
		}	
	}
	
	public void takeDmg(){
		if(hitPoints > 0){ 
			hitPoints--;
		}
	}
	
	public void resetAP(){
		actionPoints = 2;
	}
	
	public void reduceAP(){
		actionPoints--;
	}
	
	public void setOverwatch(boolean o){
		isOverWatch = o;
	}
	
	public boolean getOverwatch(){
		return isOverWatch;
	}
}
